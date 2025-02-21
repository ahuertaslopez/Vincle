package com.vincle.ahl.demo.infraestructure.adapter.out.persistence;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing an item in the persistence layer.
 */
@Entity
@Data
@Builder
@AllArgsConstructor (access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ItemEntity {
    /**
     * The unique identifier of the item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * The name of the item.
     */
    private String name;
    
    /**
     * The type of the item (e.g., bebida, comida, salsas, especies).
     */
    private String type;

    /**
     * Indicates whether the item requires refrigeration.
     */
    private boolean requiresRefrigeration;

    /**
     * The capacity of the item (e.g., 100, 1000).
     */
    private int capacity;

    /**
     * The container type of the item (e.g., botella, caja).
     */
    private String container;

    /**
     * The name of the client associated with the item.
     */
    private String clientName;

    /**
     * The timestamp when the item was created or last updated.
     */
    private LocalDateTime timestamp;

    /**
     * The status of the item (e.g., WAITING, CREATED, DELETED).
     */
    private String status;
}
