package com.vincle.ahl.demo.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vincle.ahl.demo.application.port.out.ItemRepository;
import com.vincle.ahl.demo.domain.model.Item;
import com.vincle.ahl.demo.domain.service.ItemDomainService;
import com.vincle.ahl.demo.infraestructure.adapter.out.persistence.ItemEntity;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemDomainService itemDomainService;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItems() {
        ItemEntity itemEntity = ItemEntity.builder()
                .id(1L)
                .name("Item1")
                .build();

        when(itemRepository.findAll()).thenReturn(Arrays.asList(itemEntity));

        List<Item> items = itemService.getAllItems();

        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals("Item1", items.get(0).getName());
    }

    @Test
    void testCreateItem() {
        Item item = Item.builder()
                .name("NewItem")
                .build();

        ItemEntity itemEntity = ItemEntity.builder()
                .id(1L)
                .name("NewItem")
                .status("CREATED")
                .build();

        when(itemDomainService.isValidItem(any(Item.class))).thenReturn(Arrays.asList());
        when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

        Item createdItem = itemService.createItem(item);

        assertNotNull(createdItem);
        assertEquals("NewItem", createdItem.getName());
        assertEquals("CREATED", createdItem.getStatus());
    }

    @Test
    void testGetItemById() {
        ItemEntity itemEntity = ItemEntity.builder()
                .id(1L)
                .name("Item1")
                .build();

        when(itemRepository.findById(1L)).thenReturn(Optional.of(itemEntity));

        Item item = itemService.getItemById(1L);

        assertNotNull(item);
        assertEquals("Item1", item.getName());
    }

    @Test
    void testUpdateItem() {
        Item item = Item.builder()
                .name("UpdatedItem")
                .build();

        ItemEntity itemEntity = ItemEntity.builder()
                .id(1L)
                .name("UpdatedItem")
                .build();

        when(itemRepository.findById(1L)).thenReturn(Optional.of(itemEntity));
        when(itemDomainService.isValidItem(any(Item.class))).thenReturn(Arrays.asList());
        when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

        Item updatedItem = itemService.updateItem(1L, item);

        assertNotNull(updatedItem);
        assertEquals("UpdatedItem", updatedItem.getName());
    }

    @Test
    void testDeleteItem() {
        doNothing().when(itemRepository).deleteById(1L);

        itemService.deleteItem(1L);

        verify(itemRepository, times(1)).deleteById(1L);
    }
}