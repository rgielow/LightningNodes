package com.satoshi.lightningnodes.commons.extensions


private const val ZERO = 0
fun Int?.orZero() = this?: ZERO