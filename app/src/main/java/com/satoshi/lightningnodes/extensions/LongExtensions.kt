package com.satoshi.lightningnodes.extensions


private const val ZERO = 0L
fun Long?.orZero(): Long = this ?: ZERO