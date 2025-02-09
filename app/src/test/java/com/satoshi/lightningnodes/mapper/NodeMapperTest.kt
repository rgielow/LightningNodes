package com.satoshi.lightningnodes.mapper

import com.satoshi.lightningnodes.data.mapper.toCity
import com.satoshi.lightningnodes.data.mapper.toCountry
import com.satoshi.lightningnodes.data.mapper.toNodes
import com.satoshi.lightningnodes.data.response.CityResponse
import com.satoshi.lightningnodes.data.response.CountryResponse
import com.satoshi.lightningnodes.data.response.NodeResponse
import com.satoshi.lightningnodes.domain.model.City
import com.satoshi.lightningnodes.domain.model.Country
import com.satoshi.lightningnodes.domain.model.Node
import org.junit.Assert.assertEquals
import org.junit.Test

class NodeMapperTest {

    @Test
    fun `given NodeResponse, when toNodes, then return Node`() {
        val nodeExpected = Node.mock()
        val nodeResponse = NodeResponse.mock()

        val actualNodes = listOf(nodeResponse).toNodes()

        assertEquals(listOf(nodeExpected), actualNodes)
    }


    @Test
    fun `given CityResponse, when toCity, then return City`() {
        val expectedCity = City.mock()
        val cityResponse = CityResponse.mock()

        val actualCity = cityResponse.toCity()

        assertEquals(expectedCity, actualCity)
    }

    @Test
    fun `given CountryResponse, when toCountry, then return Country`() {
        val expectedCountry = Country.mock()
        val countryResponse = CountryResponse.mock()

        val actualCountry = countryResponse.toCountry()

        assertEquals(expectedCountry, actualCountry)
    }
}
