package com.satoshi.lightningnodes.domain.model

data class Country(
    val de: String,
    val en: String,
    val es: String,
    val fr: String,
    val ja: String,
    val ptBR: String,
    val ru: String,
    val zhCN: String
) {
    companion object {
        fun mock() = Country(
            de = "Kanada",
            en = "Canada",
            es = "Canadá",
            fr = "Canada",
            ja = "カナダ",
            ptBR = "Canadá",
            ru = "Канада",
            zhCN = "加拿大"
        )
    }
}