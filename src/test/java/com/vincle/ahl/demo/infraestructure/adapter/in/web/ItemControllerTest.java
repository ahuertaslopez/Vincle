package com.vincle.ahl.demo.infraestructure.adapter.in.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.vincle.ahl.demo.application.port.in.ItemUseCase;
import com.vincle.ahl.demo.domain.model.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

class ItemControllerTest {

    @Mock
    private ItemUseCase itemUseCase;

    @InjectMocks
    private ItemController itemController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItems() throws Exception {
        Item item = Item.builder().name("Item1").build();

        List<Item> items = Arrays.asList(item);

        when(itemUseCase.getAllItems()).thenReturn(items);

        ResponseEntity<List<Item>> response = itemController.getAllItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Item1", response.getBody().get(0).getName());
    }

    @Test
    void testCreateItem() throws Exception {
        Item item = Item.builder().name("NewItem").build();

        when(itemUseCase.createItem(any(Item.class))).thenReturn(item);

        ResponseEntity<Item> response = itemController.createItem(item);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("NewItem", response.getBody().getName());
    }

    @Test
    void testGetItemById() throws Exception {
        Item item = Item.builder().name("Item1").build();

        when(itemUseCase.getItemById(1L)).thenReturn(item);

        ResponseEntity<Item> response = itemController.getItemById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Item1", response.getBody().getName());
    }

    @Test
    void testUpdateItem() throws Exception {
        Item item = Item.builder().name("UpdatedItem").build();

        when(itemUseCase.updateItem(eq(1L), any(Item.class))).thenReturn(item);

        ResponseEntity<Item> response = itemController.updateItem(1L, item);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("UpdatedItem", response.getBody().getName());
    }

    @Test
    void testDeleteItem() throws Exception {
        doNothing().when(itemUseCase).deleteItem(1L);

        ResponseEntity<Void> response = itemController.deleteItem(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(itemUseCase, times(1)).deleteItem(1L);
    }
}