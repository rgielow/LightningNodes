package com.satoshi.lightningnodes.data.mapper

import com.satoshi.lightningnodes.data.response.CityResponse
import com.satoshi.lightningnodes.data.response.CountryResponse
import com.satoshi.lightningnodes.data.response.NodeResponse
import com.satoshi.lightningnodes.domain.model.City
import com.satoshi.lightningnodes.domain.model.Country
import com.satoshi.lightningnodes.domain.model.Node
import com.satoshi.lightningnodes.extensions.orZero

fun List<NodeResponse>.toNode() = map {
    Node(
        alias = it.alias.orEmpty(),
        capacity = it.capacity.orZero(),
        channels = it.channels.orZero(),
        city = it.cityResponse?.toCity(),
        country = it.countryResponse.toCountry(),
        firstSeen = it.firstSeen.orZero(),
        publicKey = it.publicKey.orEmpty(),
        updatedAt = it.updatedAt.orZero(),
    )
}

fun CityResponse.toCity() = City(
    de = de,
    en = en,
    es = es,
    fr = fr,
    ja = ja,
    ptBR = ptBr,
    ru = ru
)

fun CountryResponse?.toCountry() = Country(
    de = this?.de.orEmpty(),
    en = this?.en.orEmpty(),
    es = this?.es.orEmpty(),
    fr = this?.fr.orEmpty(),
    ja = this?.ja.orEmpty(),
    ptBR = this?.ptBr.orEmpty(),
    ru = this?.ru.orEmpty(),
    zhCN = this?.zhCN.orEmpty()
)