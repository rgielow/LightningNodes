package com.satoshi.lightningnodes.domain.model

data class City(
    val de: String,
    val en: String,
    val es: String,
    val fr: String,
    val ja: String,
    val ptBR: String,
    val ru: String
) {
    companion object {
        fun mock() = City(
            de = "Vancouver",
            en = "Vancouver",
            es = "Vancouver",
            fr = "Vancouver",
            ja = "バンクーバー市",
            ptBR = "Vancôver",
            ru = "Ванкувер"
        )
    }
}