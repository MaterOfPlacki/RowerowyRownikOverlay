package org.rowerowyrownik.service

//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.fasterxml.jackson.module.kotlin.readValue
//import com.michaelwhyte.rowerowyrownik.client.SquareClient
//import com.michaelwhyte.rowerowyrownik.model.client.Square
//import com.michaelwhyte.rowerowyrownik.model.data.SquaresMap
import org.rowerowyrownik.client.SquareClient
import org.rowerowyrownik.model.client.Square
import org.rowerowyrownik.model.data.SquaresMap
import java.time.LocalDate

class SquareService {

    private val client: SquareClient = SquareClient()

    var squaresMap: SquaresMap = initSquaresMap()

    fun isSquareCompleted(x: Int, y: Int) = getSquareMap().isSquareCompleted(x, y)

    private fun getSquareMap(): SquaresMap {
        refreshSquaresMapIfNecessary()
        return squaresMap
    }

    private fun initSquaresMap(): SquaresMap {
        return SquaresMap(getSquareDataJson())
    }

    private fun refreshSquaresMapIfNecessary() {
        if(LocalDate.now().dayOfYear != this.squaresMap.date.dayOfYear) {
            squaresMap = initSquaresMap()
        }
    }

    private fun getSquareDataJson(): List<Square> {
//        return jacksonObjectMapper().readValue(loadResource("test-json/squares.json"))
        return client.squaresFile()
    }
}
