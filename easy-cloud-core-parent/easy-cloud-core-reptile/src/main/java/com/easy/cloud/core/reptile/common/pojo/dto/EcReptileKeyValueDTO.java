package com.easy.cloud.core.reptile.common.pojo.dto;

public class EcReptileKeyValueDTO {
	private String key;
	private String value;

	public EcReptileKeyValueDTO() {
		
	}

	public EcReptileKeyValueDTO(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
