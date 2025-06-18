package com.nexusinventory.order.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "order_items")
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    val productId: UUID,

    val sku: String,

    val quantity: Int,

    val unitPrice: BigDecimal,

    // Many OrderItems belong to one Order.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order
)