package com.cvirn.moviesearch.utils

fun Int.toCompactCurrency(): String = this.toLong().toCompactCurrency()

fun Long.toCompactCurrency(): String =
    when {
        this >= 1_000_000_000 -> String.format("%.2fB", this / 1_000_000_000.0)
        this >= 1_000_000 -> String.format("%.2fM", this / 1_000_000.0)
        this >= 1_000 -> String.format("%.2fK", this / 1_000.0)
        else -> this.toString()
    }
