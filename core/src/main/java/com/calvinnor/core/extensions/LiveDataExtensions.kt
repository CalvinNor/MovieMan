package com.calvinnor.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.calvinnor.core.domain.Result

/**
 * Check if the LiveData does not hold a value.
 */
fun LiveData<*>.isEmpty() = value == null

/**
 * Check if the LiveData holds a value.
 */
fun LiveData<*>.hasValue() = !isEmpty()

/**
 * Post a Loading State on this LiveData.
 */
fun <T> MutableLiveData<Result<T>>.postLoading(isLoading: Boolean) = postValue(Result.Loading(isLoading))

/**
 * Post a Success with Data on this LiveData.
 */
fun <T> MutableLiveData<Result<T>>.postSuccess(data: T) = postValue(Result.Success(data))

/**
 * Post a Failure with exception on this LiveData.
 */
fun <T> MutableLiveData<Result<T>>.postFailure(ex: Throwable) = postValue(Result.Failure(ex))

/**
 * Post a Result on this LiveData.
 */
fun <T> MutableLiveData<Result<T>>.postResult(result: Result<T>) {
    when (result) {

        // Whenever we want to post a Success / Failure, first notify to stop loading
        is Result.Success, is Result.Failure -> postLoading(false)
    }

    postValue(result)
}

/**
 * Wrapper over the LiveData observe for using Lambdas.
 */
inline fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, crossinline codeBlock: (data: T) -> Unit) =
    observe(lifecycleOwner, Observer { codeBlock(it) })
