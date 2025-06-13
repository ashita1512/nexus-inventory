package com.nexusinventory.inventory.controller

import com.nexusinventory.inventory.dto.AddInventoryRequest
import com.nexusinventory.inventory.dto.UpdateStockRequest
import com.nexusinventory.inventory.model.InventoryItem
import com.nexusinventory.inventory.service.InventoryItemService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/inventory")
class InventoryItemController(private val inventoryItemService: InventoryItemService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addInventory(@RequestBody request: AddInventoryRequest): InventoryItem {
        return inventoryItemService.addInventory(request)
    }

    @GetMapping("/product/{productId}")
    fun getInventoryForProduct(@PathVariable productId: UUID): List<InventoryItem> {
        return inventoryItemService.getInventoryForProduct(productId)
    }

    @PutMapping("/{id}")
    fun updateStock(@PathVariable id: UUID, @RequestBody request: UpdateStockRequest): InventoryItem {
        return inventoryItemService.updateStock(id, request)
    }
}