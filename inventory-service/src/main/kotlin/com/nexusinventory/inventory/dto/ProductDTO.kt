package com.nexusinventory.inventory.dto

import java.math.BigDecimal

// DTO for creating a new product
data class CreateProductRequest(
    val sku: String,
    val name: String,
    val description: String?,
    val price: BigDecimal
)

// --- ADD THIS NEW DTO ---
// DTO for updating an existing product
data class UpdateProductRequest(
    val name: String,
    val description: String?,
    val price: BigDecimal
)
// -------------------------