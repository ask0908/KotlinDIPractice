package com.ulternativetechnology.kotlinwithkointest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ulternativetechnology.kotlinwithkointest.koin.appModule
import org.junit.Rule
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

abstract class AbstractKoinTest: KoinTest {
    @get:Rule
    open val koinTestRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        modules(appModule)
    }

    @get:Rule
    open val mockProvider = MockProviderRule.create {
            clazz -> Mockito.mock(clazz.java)
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
}