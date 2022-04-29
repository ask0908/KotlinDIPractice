package com.ulternativetechnology.kotlinwithkointest.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import java.io.*

object ImageHelper {
    /**
     * 비트맵 이미지를 리사이즈하는 메서드
     */
    fun getResizedBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val scaleWidth = (newWidth.toFloat()) / width
        val scaleHeight = (newHeight.toFloat()) / height
        // 이미지를 조작하기 위해 Matrix 생성 후 리사이즈
        val matrix: Matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        bitmap.recycle()
        // 리사이즈한 이미지를 다른 비트맵 객체에 넣어서 만듬
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false)
    }

    /**
     * Uri -> byte[] 변환 메서드
     */
    fun getBytesFromUri(context: Context, imageUri: Uri): ByteArray {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        val byteBuffer = ByteArrayOutputStream()
        val buffer = ByteArray(1024) { 0 }
        var len = 0

        while (inputStream!!.read(buffer).also { len = it } != -1)
            byteBuffer.write(buffer, 0, len)
        return byteBuffer.toByteArray()
    }

    // Uri to bitmap
    fun uriToBitmap(context: Context, uri: Uri?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            bitmap =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {   // 안드로이드 9(API 레벨 28)이상 = 안드로이드 파이
                    ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            context.contentResolver,
                            uri!!
                        )
                    )
                } else {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }

    /* 가져온 사진 파일의 절대경로를 구하는 메서드 */
    fun createCopyAndReturnRealPath(context: Context, uri: Uri?): String? {
        val contentResolver = context.contentResolver ?: return null

        // 앱 데이터 디렉토리 안에 파일 경로 생성
        val filePath = context.applicationInfo.dataDir + File.separator + System.currentTimeMillis()
        val file = File(filePath)
        try {
            val inputStream = contentResolver.openInputStream(uri!!) ?: return null
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (ignore: IOException) {
            return null
        }
        return file.absolutePath
    }

    /* 갤러리를 갱신하는 메서드 */
    fun addPhotoToGallery(context: Context?, imagePath: String?) {
        val file = File(imagePath)
        MediaScannerConnection.scanFile(context, arrayOf(file.toString()), null, null)
    }

}