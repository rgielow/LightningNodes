package com.satoshi.lightningnodes.commons

import com.satoshi.lightningnodes.commons.extensions.orZero
import org.junit.Assert
import org.junit.Test

class IntExtensionsTest {

    @Test
    fun `given int null, when orZero, then return zero`() {
        val expectedInt = 0
        val longNumber: Int? = null

        val result = longNumber.orZero()

        Assert.assertEquals(expectedInt, result)
    }

    @Test
    fun `given int zero, when orZero, then return zero`() {
        val expectedInt = 0
        val longNumber = 0

        val result = longNumber.orZero()

        Assert.assertEquals(expectedInt, result)
    }

    @Test
    fun `given int 1000, when orZero, then return 1000`() {
        val expectedInt = 1000
        val longNumber = 1000

        val result = longNumber.orZero()

        Assert.assertEquals(expectedInt, result)
    }
}
