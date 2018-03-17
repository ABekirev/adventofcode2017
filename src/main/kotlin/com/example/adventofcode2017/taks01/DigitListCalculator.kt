package com.example.adventofcode2017.taks01

import com.example.adventofcode2017.PathFinder
import com.example.adventofcode2017.asDigits
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.asSequence

interface ListCoupleElementSearchStrategy {
    fun second(size: Int, first: Int): Int
}

fun sum(array: LongArray, strategy: ListCoupleElementSearchStrategy): Long =
        array
                .mapIndexed { index, element ->
                    val otherElement = array[strategy.second(array.size, index)]
                    if (element == otherElement) element + otherElement
                    else 0
                }
                .sum()

private fun loadArray(fileName: String): LongArray =
        Files.lines(
                PathFinder.fromResources(
                        Paths.get("com", "example", "adventofcode2017", "task01", fileName)
                                .toString()
                )
        ).use { lines ->
            lines.asSequence()
                    .flatMap(::asDigits)
                    .toList()
                    .toLongArray()
        }

private fun result(array: LongArray, strategy: ListCoupleElementSearchStrategy): Long =
        sum(array, strategy) / 2

fun main(args: Array<String>) {
    val array1 = loadArray("input1.txt")
    val array2 = loadArray("input2.txt")
    println(result(array1, GetNextListCoupleElementSearchStrategy()))
    println(result(array2, GetAfterNListCoupleElementSearchStrategy(array1.size / 2)))
}
