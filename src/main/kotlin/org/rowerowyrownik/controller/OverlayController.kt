package org.rowerowyrownik.controller

import com.michaelwhyte.rowerowyrownik.service.OverlayImageReversedService
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.rowerowyrownik.service.OverlayImageService
import org.rowerowyrownik.service.SquareService

class OverlayController {

    private val squareService = SquareService()
    private val overlayImageService: OverlayImageService = OverlayImageService(squareService)
    private val overlayImageReversedService: OverlayImageReversedService = OverlayImageReversedService(squareService)

    fun handler(): RoutingHttpHandler {
        return routes(
            "/ping" bind Method.GET to {
                Response(Status.OK).body("pong")
            },

            "/overlay/{zoom}/{x}/{y}" bind Method.GET to { request ->
                val overlay = overlayImageService.getOverlay(
                    request.path("zoom")!!.toInt(),
                    request.path("x")!!.toInt(),
                    request.path("y")!!.toInt()
                )

                Response(Status.OK).body(overlay!!.inputStream())
            },

            "/overlay-reversed/{zoom}/{x}/{y}" bind Method.GET to { request ->
                val overlay = overlayImageReversedService.getOverlay(
                    request.path("zoom")!!.toInt(),
                    request.path("x")!!.toInt(),
                    request.path("y")!!.toInt()
                )

                Response(Status.OK).body(overlay!!.inputStream())
            },
        )
    }

}