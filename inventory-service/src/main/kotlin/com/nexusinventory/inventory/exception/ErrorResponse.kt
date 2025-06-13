package com.nexusinventory.inventory.exception

import java.time.Instant

data class ErrorResponse(
    val statusCode: Int,
    val message: String?,
    val timestamp: Instant = Instant.now()
)