package com.vincle.ahl.demo.domain.constants;

/**
 * Constants used for Item validation and error messages.
 */
public class ItemConstants {

	/**
     * Error message for null Item object.
     */
	public static final String ERROR_ITEM = "El objeto Item es nulo.";
	
    /**
     * Error message for empty 'name' field.
     */
	public static final String ERROR_NAME = "El campo 'name' no puede estar vacío.";
	
    /**
     * Error message for empty 'clientName' field.
     */
	public static final String ERROR_CLIENTNAME = "El campo 'clientName' no puede estar vacío.";
	
    /**
     * Error message for invalid 'type' field.
     */
	public static final String ERROR_TYPE = "El campo 'type' debe ser 'bebida', 'comida', 'salsas' o 'especies'.";
	
    /**
     * Error message for invalid 'container' field.
     */
	public static final String ERROR_CONTAINER = "El campo 'container' debe ser 'botella' o 'caja'.";
	
    /**
     * Error message for invalid 'capacity' field.
     */
	public static final String ERROR_CAPACITY = "El campo 'capacity' debe ser 100 o 1000.";
	
    /**
     * Error message for invalid 'status' field.
     */
	public static final String ERROR_STATUS = "El campo 'status' debe ser 'WAITING', 'CREATED' o 'DELETED'.";
}
