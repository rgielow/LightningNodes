package com.satoshi.lightningnodes.data.mapper

import com.satoshi.lightningnodes.data.response.CityResponse
import com.satoshi.lightningnodes.data.response.CountryResponse
import com.satoshi.lightningnodes.data.response.NodeResponse
import com.satoshi.lightningnodes.domain.model.City
import com.satoshi.lightningnodes.domain.model.Country
import com.satoshi.lightningnodes.domain.model.Node
import com.satoshi.lightningnodes.commons.extensions.orZero

fun List<NodeResponse>.toNodes() = map {
    Node(
        alias = it.alias.orEmpty(),
        capacity = it.capacity.orZero(),
        channels = it.channels.orZero(),
        city = it.city?.toCity(),
        country = it.country?.toCountry(),
        firstSeen = it.firstSeen.orZero(),
        publicKey = it.publicKey.orEmpty(),
        updatedAt = it.updatedAt.orZero(),
    )
}

fun CityResponse.toCity() = City(
    de = de.orEmpty(),
    en = en.orEmpty(),
    es = es.orEmpty(),
    fr = fr.orEmpty(),
    ja = ja.orEmpty(),
    ptBR = ptBr.orEmpty(),
    ru = ru.orEmpty()
)

fun CountryResponse.toCountry() = Country(
    de = de.orEmpty(),
    en = en.orEmpty(),
    es = es.orEmpty(),
    fr = fr.orEmpty(),
    ja = ja.orEmpty(),
    ptBR = ptBr.orEmpty(),
    ru = ru.orEmpty(),
    zhCN = zhCn.orEmpty()
)