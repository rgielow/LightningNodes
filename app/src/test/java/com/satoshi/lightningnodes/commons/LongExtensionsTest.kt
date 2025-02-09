package com.satoshi.lightningnodes.commons

import com.satoshi.lightningnodes.commons.extensions.orZero
import com.satoshi.lightningnodes.commons.extensions.toBitcoin
import com.satoshi.lightningnodes.commons.extensions.toFormattedDate
import org.junit.Assert
import org.junit.Test

class LongExtensionsTest {
    @Test
    fun `given long null, when orZero, then return zero`() {
        val expectedLong = 0L
        val longNumber: Long? = null

        val result = longNumber.orZero()

        Assert.assertEquals(expectedLong, result)
    }

    @Test
    fun `given long zero, when orZero, then return zero`() {
        val expectedLong = 0L
        val longNumber = 0L

        val result = longNumber.orZero()

        Assert.assertEquals(expectedLong, result)
    }

    @Test
    fun `given long 1000, when orZero, then return 1000`() {
        val expectedLong = 1000L
        val longNumber = 1000L

        val result = longNumber.orZero()

        Assert.assertEquals(expectedLong, result)
    }

    @Test
    fun `given 550000 sats, when toBitcoin, then return value in BTC`() {
        val expectedBTC = "0.00550000 BTC"
        val sats = 550000L

        val result = sats.toBitcoin()

        Assert.assertEquals(expectedBTC, result)
    }

    @Test
    fun `given 5500000000 sats, when toBitcoin, then return value in BTC`() {
        val expectedBTC = "55.00000000 BTC"
        val sats = 5500000000L

        val result = sats.toBitcoin()

        Assert.assertEquals(expectedBTC, result)
    }

    @Test
    fun `given 0 sats, when toBitcoin, then return value in BTC`() {
        val expectedBTC = "0.00000000 BTC"
        val sats = 0L

        val result = sats.toBitcoin()

        Assert.assertEquals(expectedBTC, result)
    }

    @Test
    fun `given unix timestamp, when toFormattedDate, then return formatted date`() {
        val expectedBTC = "09/02/2025 às 09:29"
        val unixTimeStamp = 1739104177L

        val result = unixTimeStamp.toFormattedDate()

        Assert.assertEquals(expectedBTC, result)
    }



}