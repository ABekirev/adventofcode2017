package com.example.adventofcode2017.taks01

class GetAfterNListCoupleElementSearchStrategy(private val N: Int) : ListCoupleElementSearchStrategy {
    override fun second(size: Int, first: Int): Int = (first + N) % size
}