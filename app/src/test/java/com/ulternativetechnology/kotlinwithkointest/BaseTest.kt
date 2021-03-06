package com.ulternativetechnology.kotlinwithkointest

import com.ulternativetechnology.kotlinwithkointest.koin.appModule
import org.junit.Rule
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule

abstract class BaseTest: KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        modules(appModule)
    }

//    @get:Rule
//    val mockProvider = MockProviderRule.create {
//        clazz -> Mock
//    }
}