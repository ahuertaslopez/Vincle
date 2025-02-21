package com.vincle.ahl.demo.application.port.out;

import com.vincle.ahl.demo.infraestructure.adapter.out.persistence.ItemEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for Item entities.
 */
@Repository
public interface ItemRepository {

    /**
     * Retrieves all items.
     *
     * @return a list of all items.
     */
    List<ItemEntity> findAll();

    /**
     * Saves a given item entity.
     *
     * @param item the item to be saved.
     * @return the saved item.
     */
    ItemEntity save(ItemEntity item);

    /**
     * Retrieves an item entity by its ID.
     *
     * @param id the ID of the item to be retrieved.
     * @return the item with the specified ID.
     */
    Optional<ItemEntity> findById(Long id);

    /**
     * Deletes an item entity by its ID.
     *
     * @param id the ID of the item to be deleted.
     */
    void deleteById(Long id);
}
