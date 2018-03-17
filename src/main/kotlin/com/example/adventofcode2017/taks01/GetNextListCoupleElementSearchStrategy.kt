package com.example.adventofcode2017.taks01

class GetNextListCoupleElementSearchStrategy : ListCoupleElementSearchStrategy by delegate {
    companion object {
        val delegate: ListCoupleElementSearchStrategy = GetAfterNListCoupleElementSearchStrategy(1)
    }
}