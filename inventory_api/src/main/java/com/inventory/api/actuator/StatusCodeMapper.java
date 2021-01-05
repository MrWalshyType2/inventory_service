package com.inventory.api.actuator;

import org.springframework.boot.actuate.health.HttpCodeStatusMapper;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * Maps the status code of actuators endpoint.
 * 
 * @author morgan
 *
 */
@Component
public class StatusCodeMapper implements HttpCodeStatusMapper {

	@Override
	public int getStatusCode(Status status) {
		if (status == Status.DOWN) return 500;
		if (status == Status.OUT_OF_SERVICE) return 503;
		if (status == Status.UNKNOWN) return 500;
		return 200;
	}

	
}
