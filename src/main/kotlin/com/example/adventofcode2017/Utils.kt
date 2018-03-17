package com.example.adventofcode2017

import java.net.URI
import java.nio.file.Path
import java.nio.file.Paths

object PathFinder {
    private fun uriFromResources(strPath: String): URI = javaClass.classLoader.getResource(strPath).toURI()

    fun fromResources(strPath: String): Path = Paths.get(uriFromResources(strPath))
}

fun Char.toDigit(): Int = toInt() - 48
fun asDigits(str: String): Sequence<Long> = str.asSequence().filter(Char::isDigit).map(Char::toDigit).map(Int::toLong)