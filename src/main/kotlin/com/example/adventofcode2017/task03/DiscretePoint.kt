package com.example.adventofcode2017.task03

data class DiscretePoint(val x: Int, val y: Int) {
    companion object {
        val ZERO: DiscretePoint by lazy { DiscretePoint(0, 0) }
        val RIGHT_UNARY: DiscretePoint by lazy { abscissa(1) }
        val RIGHT_DOWN: DiscretePoint by lazy { DiscretePoint(1, -1) }
        fun abscissa(x: Int): DiscretePoint = DiscretePoint(x, 0)
        fun ordinate(y: Int): DiscretePoint = DiscretePoint(0, y)
    }

    operator fun plus(other: DiscretePoint): DiscretePoint =
            DiscretePoint(x + other.x, y + other.y)

    operator fun minus(other: DiscretePoint): DiscretePoint =
            DiscretePoint(x - other.x, y - other.y)

    operator fun unaryMinus(): DiscretePoint =
            DiscretePoint(-x, -y)
}