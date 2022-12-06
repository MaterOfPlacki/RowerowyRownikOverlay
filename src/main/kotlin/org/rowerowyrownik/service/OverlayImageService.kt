package org.rowerowyrownik.service

import java.awt.Color
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import kotlin.math.pow

class OverlayImageService(
    val squareService: SquareService
) {
    private val baseZoomLevel = 14
    private val tileColor = Color(255, 45, 206, 127)
    private val tileImage = generateSingeTile()

    fun getOverlay(zoom: Int, x: Int, y: Int): ByteArray? {
        if (zoom < baseZoomLevel) return generateZoomedOut(zoom, x, y)
        if (zoom == baseZoomLevel && squareService.isSquareCompleted(x, y)) return tileImage
        if (zoom > baseZoomLevel && isSquareCompletedForZoomedOut(zoom, x, y)) return tileImage

        return null
    }

    private fun isSquareCompletedForZoomedOut(zoom: Int, x: Int, y: Int): Boolean {
        val power = zoom - baseZoomLevel
        val scale = 2.toDouble().pow(power.toDouble())

        return squareService.isSquareCompleted((x / scale).toInt(), (y / scale).toInt())
    }

    private fun generateZoomedOut(zoom: Int, x: Int, y: Int): ByteArray? {
        val power = baseZoomLevel - zoom
        val scale = 2.toDouble().pow(power.toDouble()).toInt()

        val edge = 256
        val calculatedEdge = (edge / scale).toInt()

        val finalImage = BufferedImage(edge, edge, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = finalImage.createGraphics()
        graphics2D.color = tileColor

        for (i in 0..scale) {
            for (j in 0..scale) {
                if (squareService.isSquareCompleted((x * scale.toInt()) + i, (y * scale.toInt()) + j)) {
                    graphics2D.fill(
                        Rectangle(i * calculatedEdge, j * calculatedEdge, calculatedEdge, calculatedEdge)
                    )
                }
            }
        }

        graphics2D.dispose();

        val out = ByteArrayOutputStream()
        ImageIO.write(finalImage, "png", out)

        return out.toByteArray()
    }

    private fun generateSingeTile(): ByteArray {
        val edge = 256
        val finalImage = BufferedImage(edge, edge, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = finalImage.createGraphics()
        graphics2D.color = tileColor
        graphics2D.fill(Rectangle(0, 0, edge, edge))
        graphics2D.dispose();

        val out = ByteArrayOutputStream()
        ImageIO.write(finalImage, "png", out)

        return out.toByteArray()
    }

}