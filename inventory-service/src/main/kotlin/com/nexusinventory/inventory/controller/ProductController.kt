package com.nexusinventory.inventory.controller

import com.nexusinventory.inventory.dto.CreateProductRequest
import com.nexusinventory.inventory.dto.UpdateProductRequest // <-- NEW IMPORT
import com.nexusinventory.inventory.model.Product
import com.nexusinventory.inventory.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity // <-- NEW IMPORT
import org.springframework.web.bind.annotation.*
import java.util.UUID // <-- NEW IMPORT

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun getAllProducts(): List<Product> {
        return productService.findAllProducts()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody request: CreateProductRequest): Product {
        return productService.createProduct(request)
    }

    // --- ADD UPDATE ENDPOINT ---
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: UUID, @RequestBody request: UpdateProductRequest): Product {
        return productService.updateProduct(id, request)
    }
    // ---------------------------

    // --- ADD DELETE ENDPOINT ---
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: UUID): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }
    // ---------------------------
}