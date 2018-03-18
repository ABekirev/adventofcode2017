package com.example.adventofcode2017.task03

import com.example.adventofcode2017.task03.DiscretePoint.Companion
import kotlin.math.absoluteValue

internal data class Square(val index: Int, val startValue: Int)

internal fun findSquare(value: Int): Square {
    fun findSquare(value: Int, startValue: Int, index: Int): Square {
        val nextSquareStartValue = startValue + 8 * (index - 1)
        return when (value) {
            in startValue..(nextSquareStartValue - 1) -> Square(index, startValue)
            else -> findSquare(value, nextSquareStartValue, index + 1)
        }
    }
    return when (value) {
        1 -> Square(1, 1)
        else -> findSquare(value, 2, 2)
    }
}

internal fun findSquareStart(index: Int): DiscretePoint {
    fun findSquareStart(index: Int, prevLocation: DiscretePoint): DiscretePoint = when (index) {
        0 -> prevLocation
        else -> prevLocation + DiscretePoint.RIGHT_DOWN
    }
    return when (index) {
        1 -> DiscretePoint.ZERO
        2 -> DiscretePoint.RIGHT_UNARY
        else -> findSquareStart(index - 3, DiscretePoint.RIGHT_UNARY + DiscretePoint.RIGHT_DOWN)
    }
}

internal fun squareEdgeLength(index: Int): Int =
        2 * (index - 1)

internal fun findNumberCoordinatesWithinSquare(value: Int, startValue: Int, index: Int): DiscretePoint {
    val squareStart = findSquareStart(index)
    val squareEdgeLength = squareEdgeLength(index)
    val startToUpRightLength = squareEdgeLength - 1
    val upRightValue = startValue + startToUpRightLength
    return if (value <= upRightValue) {
        squareStart + DiscretePoint.ordinate(value - startValue)
    } else {
        val upLeftValue = upRightValue + squareEdgeLength
        val upRightCoordinates = squareStart + DiscretePoint.ordinate(startToUpRightLength)
        if (value <= upLeftValue) {
            upRightCoordinates - DiscretePoint.abscissa(value - upRightValue)
        } else {
            val downLeftValue = upLeftValue + squareEdgeLength
            val upLeftCoordinates = upRightCoordinates - DiscretePoint.abscissa(squareEdgeLength)
            if (value <= downLeftValue) {
                upLeftCoordinates - DiscretePoint.ordinate(value - upLeftValue)
            } else {
                val downLeftCoordinates = upLeftCoordinates - Companion.ordinate(squareEdgeLength)
                downLeftCoordinates + DiscretePoint.abscissa(value - downLeftValue)
            }
        }
    }
}

internal fun findNumberCoordinates(value: Int): DiscretePoint = when (value) {
    1 -> DiscretePoint.ZERO
    else -> {
        val square = findSquare(value)
        findNumberCoordinatesWithinSquare(value, square.startValue, square.index)
    }
}

internal fun findManhattanDistance(point: DiscretePoint): Int =
        point.x.absoluteValue + point.y.absoluteValue

internal fun findManhattanDistance(firstPoint: DiscretePoint, secondPoint: DiscretePoint): Int =
        findManhattanDistance(secondPoint - firstPoint)

fun main(args: Array<String>) {
    println(findManhattanDistance(findNumberCoordinates(361527)))
}
