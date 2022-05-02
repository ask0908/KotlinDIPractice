package com.ulternativetechnology.kotlinwithkointest.kointest

import com.google.gson.annotations.SerializedName

data class ServerResponse(@SerializedName("data") val result: Boolean)