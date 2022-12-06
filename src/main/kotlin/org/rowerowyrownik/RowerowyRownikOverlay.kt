package org.rowerowyrownik

import org.http4k.core.HttpHandler
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.rowerowyrownik.controller.OverlayController

fun main() {
    val overlayController = OverlayController()
    val printingApp: HttpHandler = PrintRequest().then(overlayController.handler())
    val server = printingApp.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())

//    val squareClient = SquareClient()
//    println(squareClient.squaresFile())
}
