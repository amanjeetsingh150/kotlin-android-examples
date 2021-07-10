package com.developers.usingretrofit.utils

fun <T> lazyUI(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { initializer.invoke() }