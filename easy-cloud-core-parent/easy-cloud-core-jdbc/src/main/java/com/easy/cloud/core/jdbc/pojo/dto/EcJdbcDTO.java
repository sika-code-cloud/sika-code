package com.easy.cloud.core.jdbc.pojo.dto;

import java.lang.reflect.Field;
import java.util.List;


public class EcJdbcDTO {
	private List<Field> entityFields;

	public List<Field> getEntityFields() {
		return entityFields;
	}

	public void setEntityFields(List<Field> entityFields) {
		this.entityFields = entityFields;
	}
	
}
