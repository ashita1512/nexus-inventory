package com.nexusinventory.inventory.dto

import java.util.UUID

// DTO for adding a product to a warehouse's inventory for the first time
data class AddInventoryRequest(
    val productId: UUID,
    val warehouseId: UUID,
    val quantity: Int
)

// DTO for making a simple stock adjustment
data class UpdateStockRequest(
    val newQuantity: Int
)