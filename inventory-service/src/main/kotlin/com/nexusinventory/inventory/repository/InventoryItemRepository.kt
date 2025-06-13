package com.nexusinventory.inventory.repository

import com.nexusinventory.inventory.model.InventoryItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface InventoryItemRepository : JpaRepository<InventoryItem, UUID> {

    // A custom method to find all inventory records for a given product
    fun findByProductId(productId: UUID): List<InventoryItem>
}