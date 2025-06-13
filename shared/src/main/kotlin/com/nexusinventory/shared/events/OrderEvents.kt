package com.nexusinventory.shared.events

import java.math.BigDecimal
import java.util.UUID

// This event is published when an order is successfully created.
data class OrderCreatedEvent(
    val orderId: UUID,
    val customerId: UUID,
    val items: List<LineItem>
)

data class LineItem(
    val productId: UUID,
    val sku: String,
    val quantity: Int
)