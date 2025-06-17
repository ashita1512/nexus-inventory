package com.nexusinventory.inventory.service

import com.nexusinventory.inventory.dto.AddInventoryRequest
import com.nexusinventory.inventory.dto.UpdateStockRequest
import com.nexusinventory.inventory.model.InventoryItem
import com.nexusinventory.inventory.repository.InventoryItemRepository
import com.nexusinventory.inventory.repository.ProductRepository
import com.nexusinventory.shared.events.InventoryUpdatedEvent
import org.springframework.stereotype.Service
import java.util.*

@Service
class InventoryItemService(
    private val inventoryItemRepository: InventoryItemRepository,
    private val productRepository: ProductRepository,
    private val kafkaProducerService: KafkaProducerService
) {

    // Restoring the logic for this method
    fun addInventory(request: AddInventoryRequest): InventoryItem {
        if (!productRepository.existsById(request.productId)) {
            throw NoSuchElementException("Product with ID ${request.productId} not found.")
        }

        val newInventoryItem = InventoryItem(
            productId = request.productId,
            warehouseId = request.warehouseId,
            quantityAvailable = request.quantity,
            quantityReserved = 0
        )
        return inventoryItemRepository.save(newInventoryItem)
    }

    // Restoring the logic for this method
    fun getInventoryForProduct(productId: UUID): List<InventoryItem> {
        return inventoryItemRepository.findByProductId(productId)
    }

    fun updateStock(id: UUID, request: UpdateStockRequest): InventoryItem {
        val inventoryItem = inventoryItemRepository.findById(id)
            .orElseThrow { NoSuchElementException("Inventory item with ID $id not found.") }

        inventoryItem.quantityAvailable = request.newQuantity

        val savedItem = inventoryItemRepository.save(inventoryItem)

        val event = InventoryUpdatedEvent(
            inventoryItemId = savedItem.id!!,
            productId = savedItem.productId,
            warehouseId = savedItem.warehouseId, // <-- THE MISSING PARAMETER IS NOW ADDED
            newQuantityAvailable = savedItem.quantityAvailable
        )
        kafkaProducerService.sendInventoryUpdateEvent(event)

        return savedItem
    }
}