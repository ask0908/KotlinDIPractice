package com.ulternativetechnology.kotlinwithkointest.Flow

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpbitResponse(
    val unit: Int,
    val candles: List<CoinPrice>
)
@JsonClass(generateAdapter = true)
data class CoinPrice(
    val tradePrice: Float
)