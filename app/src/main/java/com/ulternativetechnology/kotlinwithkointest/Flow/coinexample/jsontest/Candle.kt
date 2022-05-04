package com.ulternativetechnology.kotlinwithkointest.Flow.coinexample.jsontest

data class Candle(
    val candleAccTradePrice: Double,
    val candleAccTradeVolume: Double,
    val candleDateTime: String,
    val candleDateTimeKst: String,
    val code: String,
    val highPrice: Double,
    val lowPrice: Double,
    val openingPrice: Double,
    val timestamp: Long,
    val tradePrice: Double,
    val unit: Int
)