package com.example.adventofcode2017.task02

import com.example.adventofcode2017.longLinesStream
import com.example.adventofcode2017.sum
import java.nio.file.Paths
import java.util.stream.Stream

private fun checksum(stream: Stream<List<Long>>, lineChecksumFunc: (List<Long>) -> Long): Long =
        stream
                .map(lineChecksumFunc)
                .sum()

private fun findDividedEvenly(list: List<Long>): Pair<Long, Long>? =
        list
                .mapIndexed { index, value ->
                    Pair(index, value)
                }
                .flatMap { pair ->
                    list
                            .filterIndexed { index, _ ->
                                index != pair.first
                            }
                            .map { value: Long ->
                                Pair(pair.second, value)
                            }
                }
                .find { pair ->
                    (pair.first % pair.second).toInt() == 0
                }

fun main(args: Array<String>) {
    val inputPath = Paths.get("com", "example", "adventofcode2017", "task02", "input.txt")
    println(
            checksum(longLinesStream(inputPath)) { list ->
                val max = (list.max() ?: throw IllegalArgumentException("Max is not found"))
                val min = (list.min() ?: throw IllegalArgumentException("Min is not found"))
                return@checksum max - min
            }
    )
    println(
            checksum(longLinesStream(inputPath)) { list ->
                findDividedEvenly(list).let { pair ->
                    if (pair != null) pair.first / pair.second
                    else 0
                }
            }
    )
}

