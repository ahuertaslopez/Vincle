package com.vincle.ahl.demo.application.port.in;

import java.util.List;

import com.vincle.ahl.demo.domain.model.Item;

/**
 * Interface for Item Use Case operations.
 */
public interface ItemUseCase {
    
    /**
     * Retrieves all items.
     *
     * @return a list of all items.
     */
    List<Item> getAllItems();

    /**
     * Creates a new item.
     *
     * @param item the item to be created.
     * @return the created item.
     */
    Item createItem(Item item);

    /**
     * Retrieves an item by its ID.
     *
     * @param id the ID of the item to be retrieved.
     * @return the item with the specified ID.
     */
    Item getItemById(Long id);

    /**
     * Updates an existing item.
     *
     * @param id the ID of the item to be updated.
     * @param item the updated item details.
     * @return the updated item.
     */    
    Item updateItem(Long id, Item item);

    /**
     * Deletes an item by its ID.
     *
     * @param id the ID of the item to be deleted.
     */
    void deleteItem(Long id);
    
}
