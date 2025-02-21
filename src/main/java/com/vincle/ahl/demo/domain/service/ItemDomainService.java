package com.vincle.ahl.demo.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.util.ObjectUtils;

import com.vincle.ahl.demo.domain.constants.ItemConstants;
import com.vincle.ahl.demo.domain.model.Item;

/**
 * Service class for domain logic related to Items.
 */
public class ItemDomainService {

    private static final Set<String> VALID_TYPES = Set.of("bebida", "comida", "salsas", "especies");
    private static final Set<String> VALID_CONTAINER = Set.of("botella", "caja");
    private static final Set<Integer> VALID_CAPACITIES = Set.of(100, 1000);
    private static final Set<String> VALID_STATUSES = Set.of("WAITING", "CREATED", "DELETED");

    /**
     * Validates an Item object.
     *
     * @param item the item to be validated
     * @return a list of error messages if the item is invalid, or an empty list if the item is valid
     */
    public List<String> isValidItem(Item item) {
    	List<String> errors = new ArrayList<>();

		if (ObjectUtils.isEmpty(item)) {
			errors.add(ItemConstants.ERROR_ITEM);
		} else {
			if (ObjectUtils.isEmpty(item.getName())) {
				errors.add(ItemConstants.ERROR_NAME);
			}
			if (ObjectUtils.isEmpty(item.getClientName())) {
				errors.add(ItemConstants.ERROR_CLIENTNAME);
			}
			if (!VALID_TYPES.contains(item.getType())) {
				errors.add(ItemConstants.ERROR_TYPE);
			}
			if (!VALID_CONTAINER.contains(item.getContainer())) {
				errors.add(ItemConstants.ERROR_CONTAINER);
			}
			if (!VALID_CAPACITIES.contains(item.getCapacity())) {
				errors.add(ItemConstants.ERROR_CAPACITY);
			}
			if (!VALID_STATUSES.contains(item.getStatus())) {
				errors.add(ItemConstants.ERROR_STATUS);
			}
		}

		return errors;
    }
    
    /**
     * Calculates the total weight of a set of Items.
     *
     * @param items the items whose total weight is to be calculated
     * @return the total weight of the items
     */
    public int calculateTotalWeight(Item... items) {
        int totalWeight = 0;
        for (Item item : items) {
            totalWeight += item.getCapacity();
        }
        return totalWeight;
    }

}
