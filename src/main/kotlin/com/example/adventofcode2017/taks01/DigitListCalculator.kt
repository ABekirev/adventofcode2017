package com.example.adventofcode2017.taks01

import com.example.adventofcode2017.asDigits
import com.example.adventofcode2017.linesFromResource
import java.nio.file.Paths
import kotlin.streams.asSequence

interface ListCoupleElementSearchStrategy {
    fun second(size: Int, first: Int): Int
}

private fun sum(array: LongArray, strategy: ListCoupleElementSearchStrategy): Long =
        array
                .mapIndexed { index, element ->
                    val otherElement = array[strategy.second(array.size, index)]
                    if (element == otherElement) element + otherElement
                    else 0
                }
                .sum()

private fun loadArray(fileName: String): LongArray {
    return linesFromResource(Paths.get("com", "example", "adventofcode2017", "task01", fileName))
            .use { lines ->
                lines.asSequence()
                        .flatMap(::asDigits)
                        .toList()
                        .toLongArray()
            }
}

private fun result(array: LongArray, strategy: ListCoupleElementSearchStrategy): Long =
        sum(array, strategy) / 2

fun main(args: Array<String>) {
    val array = loadArray("input.txt")
    println(result(array, GetNextListCoupleElementSearchStrategy()))
    println(result(array, GetAfterNListCoupleElementSearchStrategy(array.size / 2)))
}
