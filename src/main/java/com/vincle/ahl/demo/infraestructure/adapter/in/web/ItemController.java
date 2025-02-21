package com.vincle.ahl.demo.infraestructure.adapter.in.web;

import com.vincle.ahl.demo.application.port.in.ItemUseCase;
import com.vincle.ahl.demo.domain.model.Item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing items.
 */
@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    private final ItemUseCase itemUseCase;

    /**
     * Constructs an ItemController with the specified use case.
     *
     * @param itemUseCase the item use case
     */
    public ItemController(ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;
    }

    /**
     * Retrieves all items.
     *
     * @return a ResponseEntity containing a list of all items and an HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemUseCase.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    /**
     * Creates a new item.
     *
     * @param item the item to be created
     * @return a ResponseEntity containing the created item and an HTTP status code
     */
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemUseCase.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    /**
     * Retrieves an item by its ID.
     *
     * @param id the ID of the item to be retrieved
     * @return a ResponseEntity containing the item with the specified ID and an HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemUseCase.getItemById(id));
    }

    /**
     * Updates an existing item.
     *
     * @param id the ID of the item to be updated
     * @param item the updated item details
     * @return a ResponseEntity containing the updated item and an HTTP status code
     */
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        return ResponseEntity.ok(itemUseCase.updateItem(id, item));
    }

    /**
     * Deletes an item by its ID.
     *
     * @param id the ID of the item to be deleted
     * @return a ResponseEntity with an HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemUseCase.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

}
