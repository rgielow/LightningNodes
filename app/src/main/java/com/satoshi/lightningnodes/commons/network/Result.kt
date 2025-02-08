package com.satoshi.lightningnodes.commons.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure(val error: String) : Result<Nothing>()
}

fun <T> Result<T>.fold(
    onSuccess: (T) -> Unit,
    onFailure: (String) -> Unit
) {
    return when (this) {
        is Result.Failure -> onFailure(error)
        is Result.Success -> onSuccess(data)
    }
}

suspend fun <T> safeRunDispatcher(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend CoroutineScope.() -> (T)
) = withContext(dispatcher) {
    return@withContext try {
        val result = block()
        Result.Success(result)
    } catch (ex: Exception) {
        Result.Failure(ex.message.orEmpty())
    }
}