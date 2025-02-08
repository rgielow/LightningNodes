package com.satoshi.lightningnodes.commons.extensions


private const val ZERO = 0L
fun Long?.orZero(): Long = this ?: ZERO