package com.nexusinventory.inventory.service

import com.nexusinventory.inventory.dto.AddInventoryRequest
import com.nexusinventory.inventory.dto.UpdateStockRequest
import com.nexusinventory.inventory.model.InventoryItem
import com.nexusinventory.inventory.repository.InventoryItemRepository
import com.nexusinventory.inventory.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InventoryItemService(
    private val inventoryItemRepository: InventoryItemRepository,
    private val productRepository: ProductRepository // Inject ProductRepository to verify products exist
) {

    fun addInventory(request: AddInventoryRequest): InventoryItem {
        // First, check if the product exists before adding inventory for it
        if (!productRepository.existsById(request.productId)) {
            throw NoSuchElementException("Product with ID ${request.productId} not found.")
        }

        val newInventoryItem = InventoryItem(
            productId = request.productId,
            warehouseId = request.warehouseId,
            quantityAvailable = request.quantity,
            quantityReserved = 0 // Initially, no stock is reserved
        )
        return inventoryItemRepository.save(newInventoryItem)
    }

    fun getInventoryForProduct(productId: UUID): List<InventoryItem> {
        return inventoryItemRepository.findByProductId(productId)
    }

    fun updateStock(id: UUID, request: UpdateStockRequest): InventoryItem {
        val inventoryItem = inventoryItemRepository.findById(id)
            .orElseThrow { NoSuchElementException("Inventory item with ID $id not found.") }

        inventoryItem.quantityAvailable = request.newQuantity

        return inventoryItemRepository.save(inventoryItem)
    }
}