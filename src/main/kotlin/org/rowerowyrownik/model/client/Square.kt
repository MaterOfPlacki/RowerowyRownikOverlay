package org.rowerowyrownik.model.client

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Square(
    val x: Int,
    val y: Int
)