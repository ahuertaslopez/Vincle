package com.vincle.ahl.demo.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.vincle.ahl.demo.domain.constants.ItemConstants;
import com.vincle.ahl.demo.domain.model.Item;

class ItemDomainServiceTest {

    @InjectMocks
    private ItemDomainService itemDomainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsValidItem() {
        Item validItem = Item.builder()
                .name("Item1")
                .clientName("Client1")
                .type("bebida")
                .container("botella")
                .capacity(100)
                .status("CREATED")
                .build();

        List<String> errors = itemDomainService.isValidItem(validItem);

        assertTrue(errors.isEmpty(), "Valid item should not have errors");

        Item invalidItem = Item.builder()
                .name("")
                .clientName("")
                .type("invalidType")
                .container("invalidContainer")
                .capacity(999)
                .status("invalidStatus")
                .build();

        errors = itemDomainService.isValidItem(invalidItem);

        assertFalse(errors.isEmpty(), "Invalid item should have errors");
        assertTrue(errors.contains(ItemConstants.ERROR_NAME));
        assertTrue(errors.contains(ItemConstants.ERROR_CLIENTNAME));
        assertTrue(errors.contains(ItemConstants.ERROR_TYPE));
        assertTrue(errors.contains(ItemConstants.ERROR_CONTAINER));
        assertTrue(errors.contains(ItemConstants.ERROR_CAPACITY));
        assertTrue(errors.contains(ItemConstants.ERROR_STATUS));
    }

    @Test
    void testCalculateTotalWeight() {
        Item item1 = Item.builder().capacity(100).build();

        Item item2 = Item.builder().capacity(1000).build();

        int totalWeight = itemDomainService.calculateTotalWeight(item1, item2);

        assertEquals(1100, totalWeight, "Total weight should be 1100");
    }
}