package org.rowerowyrownik.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.http4k.client.ApacheClient
import org.http4k.core.BodyMode
import org.http4k.core.Method
import org.http4k.core.Request
import org.rowerowyrownik.model.client.Square

class SquareClient {

    private val client = ApacheClient(responseBodyMode = BodyMode.Memory)
    private val mapper = jacksonObjectMapper()

    private val squaresJsonUri = "https://rowerowyrownik.pl/mapa/square.json"

    fun squaresFile(): List<Square> {
        val response = client(Request(Method.GET, squaresJsonUri))
        return mapper.readValue(response.body.stream)
    }

}