package org.rowerowyrownik.model.data

import org.rowerowyrownik.model.client.Square
import java.time.LocalDate

class SquaresMap(list: List<Square>) {
    val date: LocalDate = LocalDate.now()

    private val offsetPosX: Int
    private val offsetPosY: Int

    private val maxPosX: Int
    private val maxPosY: Int

    private val sizeX: Int
    private val sizeY: Int

    private val squareArray: Array<Array<Boolean>>

    init {
        val xRange = getXRange(list)
        val yRange = getYRange(list)
        offsetPosX = xRange.first
        maxPosX = xRange.second
        offsetPosY = yRange.first
        maxPosY = yRange.second
        sizeX = maxPosX - offsetPosX + 1
        sizeY = maxPosY - offsetPosY + 1
        squareArray = createArray(list)
    }

    private fun getXRange(list: List<Square>): Pair<Int, Int> {
        val sortedByX = list.sortedBy { it -> it.x }

        return Pair(sortedByX.first().x, sortedByX.last().x)
    }

    private fun getYRange(list: List<Square>): Pair<Int, Int> {
        val sortedByX = list.sortedBy { it -> it.y }

        return Pair(sortedByX.first().y, sortedByX.last().y)
    }

    private fun createArray(list: List<Square>): Array<Array<Boolean>>{
        val array = Array(sizeX) {Array(sizeY) {false} }

        list.forEach { square -> array[square.x - offsetPosX][square.y - offsetPosY] = true }

        return array
    }

    public fun isSquareCompleted(x: Int, y: Int): Boolean {
        if (x !in (offsetPosX..maxPosX)) return false
        if (y !in (offsetPosY..maxPosY)) return false

        return squareArray[x - offsetPosX][y - offsetPosY]
    }
}
