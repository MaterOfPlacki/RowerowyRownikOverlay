package org.rowerowyrownik.controller

import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.rowerowyrownik.formats.JacksonMessage
import org.rowerowyrownik.formats.jacksonMessageLens

fun handleRoutes() = routes(
    "/ping" bind GET to {
        Response(Status.OK).body("pong")
    },

    "/formats/json/jackson" bind GET to {
        Response(Status.OK).with(jacksonMessageLens of JacksonMessage("Barry", "Hello there!"))
    },
)