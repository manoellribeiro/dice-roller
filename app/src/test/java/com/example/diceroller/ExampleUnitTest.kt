package com.example.diceroller

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun rollDiceFunctionIsWorking(){

        val mainActivityClass = MainActivity()
        mainActivityClass.rollDice()

    }
}
