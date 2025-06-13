package com.nexusinventory.inventory.model

import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "inventory_items")
class InventoryItem(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(name = "product_id", nullable = false)
    val productId: UUID,

    @Column(name = "warehouse_id", nullable = false)
    val warehouseId: UUID,

    @Column(name = "quantity_available", nullable = false)
    var quantityAvailable: Int,

    @Column(name = "quantity_reserved", nullable = false)
    var quantityReserved: Int,

    @Version // For optimistic locking
    val version: Long = 0,

    @Column(name = "last_updated")
    var lastUpdated: Instant = Instant.now()
)