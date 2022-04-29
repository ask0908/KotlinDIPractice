package com.ulternativetechnology.kotlinwithkointest.util

import android.util.Log
import java.lang.NumberFormatException
import java.text.DecimalFormat

object StringUtils {

    val TAG: String = this.javaClass.simpleName

    /**
     * 매개변수로 받은 문자열을 구분자에 맞게 split()한 다음 List<String> 형태로 리턴하는 메서드
     * @param separator - 구분자로 사용할 키워드
     * @param sentence - split()의 대상 문자열
     * @return - List<String> 형태의 분리된 문자열들
     */
    fun splitSentence(separator: String, sentence: String): List<String> {
        return sentence.split(separator).toList()
    }

    /**
     * 문자열이 null일 경우 공백으로 리턴되게 하는 메서드
     */
    fun notNullString(str: String): String {
        return if ("null" == str) {
            ""
        } else {
            str
        }
    }

    /**
     * Object로 받은 문자열이 null일 경우 공백으로 리턴되게 하는 메서드
     */
    fun notNullString(str: Any): String {
        return if ("null" == str) {
            ""
        } else {
            str as String
        }
    }

    /**
     * 매개변수로 받은 문자열이 null인지 확인하는 메서드
     */
    fun isEmptyString(str: String): Boolean {
        return notNullString(str) == "" || notNullString(str) == "null" || notNullString(str).equals(null) || str.trim().isEmpty()
    }

    // CharSequence를 받은 경우
    fun isEmptyString(str: CharSequence): Boolean {
        return isEmptyString(str.toString())
    }

    /**
     * long 형태의 숫자 데이터에 화폐 단위를 적용해서 String 형태로 리턴시키는 메서드
     */
    fun setMoneyForm(data: Long): String {
        return DecimalFormat(",###").format(data)
    }

    fun setMoneyForm(data: String): String {
        if (isEmptyString(data)) return "0"
        try {
            return setMoneyForm(data.replace("[^0-9]", "").toLong())
        }   catch (e: NumberFormatException) {
            Log.e(TAG, "변환 실패")
        }
        return "0"
    }

    /**
     * 모든 자료형의 null check 메서드
     */
    fun isEmpty(obj: Any): Boolean {
        if (obj is String) {
            if (obj == null || obj.trim().isEmpty() || "null" == obj) return true
        } else {
            if (obj == null) return true
        }
        return false
    }

    fun getString(value: String, defaultValue: String): String {
        return if (isEmpty(value)) defaultValue
        else value
    }

    fun getString(item: HashMap<String, String>, key: String, defaultValue: String): String {
        if (isEmptyString(item[key]!!)) {
            return defaultValue
        }
        return item[key]!!
    }

    fun getString(value: String): String {
        return getString(value, "")
    }

}