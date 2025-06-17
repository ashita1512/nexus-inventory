package com.nexusinventory.inventory.service

import com.nexusinventory.shared.events.InventoryUpdatedEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        const val INVENTORY_TOPIC = "inventory-updates"
    }

    fun sendInventoryUpdateEvent(event: InventoryUpdatedEvent) {
        logger.info("Publishing event to topic '{}': {}", INVENTORY_TOPIC, event)
        kafkaTemplate.send(INVENTORY_TOPIC, event)
    }
}