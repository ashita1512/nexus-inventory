package com.nexusinventory.shared.events

import java.time.Instant
import java.util.UUID

// This event will be published whenever an inventory item's stock is updated.
data class InventoryUpdatedEvent(
    val inventoryItemId: UUID,
    val productId: UUID,
    val newQuantityAvailable: Int,
    val timestamp: Instant = Instant.now()
)