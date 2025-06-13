package com.nexusinventory.inventory.service

import com.nexusinventory.inventory.dto.CreateProductRequest
import com.nexusinventory.inventory.dto.UpdateProductRequest // <-- NEW IMPORT
import com.nexusinventory.inventory.model.Product
import com.nexusinventory.inventory.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.* // <-- NEW IMPORT for UUID

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun createProduct(request: CreateProductRequest): Product {
        val newProduct = Product(
            sku = request.sku,
            name = request.name,
            description = request.description,
            price = request.price
        )
        return productRepository.save(newProduct)
    }

    // --- ADD UPDATE METHOD ---
    fun updateProduct(id: UUID, request: UpdateProductRequest): Product {
        // Find the existing product or throw an exception if not found
        val existingProduct = productRepository.findById(id)
            .orElseThrow { NoSuchElementException("Product with ID $id not found") }

        // Update its properties
        existingProduct.name = request.name
        existingProduct.description = request.description
        existingProduct.price = request.price

        // Save the updated product back to the database
        return productRepository.save(existingProduct)
    }
    // -------------------------

    // --- ADD DELETE METHOD ---
    fun deleteProduct(id: UUID) {
        if (!productRepository.existsById(id)) {
            throw NoSuchElementException("Product with ID $id not found")
        }
        productRepository.deleteById(id)
    }
    // -------------------------
}