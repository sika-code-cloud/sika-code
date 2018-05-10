package com.easy.cloud.core.jdbc.primarykey.test;

import java.io.Serializable;
import java.util.UUID;

import com.easy.cloud.core.jdbc.primarykey.EcBasePrimaryKeyGenerator;

public class EcDemoPrimaryKeyGenerator implements EcBasePrimaryKeyGenerator{

	@Override
	public Serializable generate(Object entityObj) {
		return UUID.randomUUID().toString();
	}

}
