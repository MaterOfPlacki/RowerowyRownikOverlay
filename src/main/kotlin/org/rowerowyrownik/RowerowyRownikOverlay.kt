package org.rowerowyrownik

import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.rowerowyrownik.controller.OverlayController

fun main() {
    val overlayController = OverlayController()
    val handler = overlayController.handler()
    val server = handler.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())
}
