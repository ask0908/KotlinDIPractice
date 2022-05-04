package com.ulternativetechnology.kotlinwithkointest.Flow.coinexample.jsontest

data class CoinData(
    val businessTime: BusinessTime,
    val candles: List<Candle>,
    val prevClosingPrice: Double,
    val unit: Int
)