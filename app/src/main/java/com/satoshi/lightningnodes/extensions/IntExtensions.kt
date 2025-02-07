package com.satoshi.lightningnodes.extensions


private const val ZERO = 0
fun Int?.orZero() = this?: ZERO