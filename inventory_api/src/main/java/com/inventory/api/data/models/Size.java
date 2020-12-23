package com.inventory.api.data.models;

import lombok.Value;

/**
 * The size of an object by height, width and length.
 * 
 * IMMUTABLE
 * 
 * @author morgan
 *
 */
@Value
public class Size {

	private Double width;
	private Double height;
	private Double length;
}
