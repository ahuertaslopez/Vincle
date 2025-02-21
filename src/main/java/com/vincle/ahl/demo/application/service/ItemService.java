package com.vincle.ahl.demo.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vincle.ahl.demo.application.port.in.ItemUseCase;
import com.vincle.ahl.demo.application.port.out.ItemRepository;
import com.vincle.ahl.demo.domain.model.Item;
import com.vincle.ahl.demo.domain.service.ItemDomainService;
import com.vincle.ahl.demo.infraestructure.adapter.out.persistence.ItemEntity;

/**
 * Service class for managing items.
 */
@Service
public class ItemService implements ItemUseCase {

    private ItemRepository itemRepository;
    private ItemDomainService itemDomainService;

    /**
     * Constructs an ItemService with the specified repository and domain service.
     *
     * @param itemRepository the item repository
     * @param itemDomainService the item domain service
     */
    public ItemService(ItemRepository itemRepository, ItemDomainService itemDomainService) {
        this.itemRepository = itemRepository;
        this.itemDomainService = itemDomainService;
    }

    /**
     * Retrieves all items.
     *
     * @return a list of all items
     */
    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new item.
     *
     * @param item the item to be created
     * @return the created item
     */
    @Override
    public Item createItem(Item item) {
        item.setTimestamp(LocalDateTime.now());
        item.setStatus("CREATED");
        
        List<String> errors = itemDomainService.isValidItem(item);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Invalid item: " + String.join(" | ", errors));
        }
        ItemEntity itemEntity = toEntity(item);
        return toDomain(itemRepository.save(itemEntity));
    }

    /**
     * Retrieves an item by its ID.
     *
     * @param id the ID of the item to be retrieved
     * @return the item with the specified ID
     */
    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .map(this::toDomain)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    /**
     * Updates an existing item.
     *
     * @param id the ID of the item to be updated
     * @param item the updated item details
     * @return the updated item
     */
    @Override
    public Item updateItem(Long id, Item item) {
        Item existingItem = getItemById(id);
        existingItem.setName(item.getName());
        existingItem.setType(item.getType());
        existingItem.setRequiresRefrigeration(item.isRequiresRefrigeration());
        existingItem.setCapacity(item.getCapacity());
        existingItem.setContainer(item.getContainer());
        existingItem.setClientName(item.getClientName());
        existingItem.setTimestamp(LocalDateTime.now());
        existingItem.setStatus(item.getStatus());

        List<String> errors = itemDomainService.isValidItem(existingItem);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Invalid item: " + String.join(" | ", errors));
        }
        
        ItemEntity itemEntity = toEntity(existingItem);
        return toDomain(itemRepository.save(itemEntity));
    }

    /**
     * Deletes an item by its ID.
     *
     * @param id the ID of the item to be deleted
     */
    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
    
    /**
     * Converts an ItemEntity to a domain Item.
     *
     * @param entity the item entity
     * @return the domain item
     */
    private Item toDomain(ItemEntity entity) {
        Item item = Item.builder()
        		.id(entity.getId())
        		.name(entity.getName())
        		.type(entity.getType())
        		.requiresRefrigeration(entity.isRequiresRefrigeration())
        		.capacity(entity.getCapacity())
        		.container(entity.getContainer())
        		.clientName(entity.getClientName())
        		.timestamp(entity.getTimestamp())
        		.status(entity.getStatus())
        		.build();
        return item;
    }

    /**
     * Converts a domain Item to an ItemEntity.
     *
     * @param item the domain item
     * @return the item entity
     */
    private ItemEntity toEntity(Item item) {
        ItemEntity entity = ItemEntity.builder()
        		.id(item.getId())
        		.name(item.getName())
        		.type(item.getType())
        		.requiresRefrigeration(item.isRequiresRefrigeration())
        		.capacity(item.getCapacity())
        		.container(item.getContainer())
        		.clientName(item.getClientName())
        		.timestamp(item.getTimestamp())
        		.status(item.getStatus())
        		.build();
        return entity;
    }

}
