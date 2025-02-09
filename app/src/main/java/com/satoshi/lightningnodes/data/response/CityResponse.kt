package com.satoshi.lightningnodes.data.response

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("de") val de: String?,
    @SerializedName("en") val en: String?,
    @SerializedName("es") val es: String?,
    @SerializedName("fr") val fr: String?,
    @SerializedName("ja") val ja: String?,
    @SerializedName("pt-BR") val ptBr: String?,
    @SerializedName("ru") val ru: String?,
    @SerializedName("zh-CN") val zhCn: String?,
) {
    companion object {
        fun mock() = CityResponse(
            de = "Vancouver",
            en = "Vancouver",
            es = "Vancouver",
            fr = "Vancouver",
            ja = "バンクーバー市",
            ptBr = "Vancôver",
            ru = "Ванкувер",
            zhCn = "加拿大"
        )
    }
}