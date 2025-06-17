package com.nexusinventory.shared.events

import java.time.Instant
import java.util.UUID

// This event is published whenever an inventory item's stock is updated.
data class InventoryUpdatedEvent(
    val inventoryItemId: UUID,
    val productId: UUID,
    val warehouseId: UUID,
    val newQuantityAvailable: Int,
    val timestamp: Instant = Instant.now()
)