package com.nexusinventory.order.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(unique = true, nullable = false)
    val orderNumber: String,

    @Column(nullable = false)
    val customerId: UUID,

    @Column(nullable = false)
    var status: String = "PENDING",

    @Column(nullable = false)
    val totalAmount: BigDecimal,

    // An Order can have many OrderItems.
    // `CascadeType.ALL` means if we save an Order, its items are also saved.
    // `orphanRemoval = true` means if we remove an item from this list, it gets deleted from the DB.
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: List<OrderItem> = mutableListOf(),

    @Column(name = "created_at", updatable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at")
    var updatedAt: Instant = Instant.now()
)