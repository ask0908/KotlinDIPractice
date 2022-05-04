package com.ulternativetechnology.kotlinwithkointest.city

class SleepTimeUseCase {
    fun isNight(hour:Int): Boolean {
        if (hour > 24) throw IllegalArgumentException()
        if (hour > 21 || hour < 7) return true
        return false
    }
}