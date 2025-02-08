package com.satoshi.lightningnodes.commons.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val ZERO = 0L
private const val ONE_THOUSAND = 1000
private const val SATOSHI_IN_ONE_BITCOIN = 100000000L
private const val DATE_FORMAT_YYYY_MM_DD_T_MM_SS = "dd/MM/yyyy 'às' HH:mm"

fun Long?.orZero() = this ?: ZERO

fun Long.toBitcoin(): String {
    val btc = this.toDouble() / SATOSHI_IN_ONE_BITCOIN
    return "%.8f BTC".format(btc)
}

fun Long.toFormattedDate(): String {
    val sdf = SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_MM_SS, Locale.getDefault())
    val date = Date(this * ONE_THOUSAND)
    return sdf.format(date)
}