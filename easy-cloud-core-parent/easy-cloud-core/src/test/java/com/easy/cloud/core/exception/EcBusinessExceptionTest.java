package com.easy.cloud.core.exception;

import org.junit.Test;

import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

public class EcBusinessExceptionTest {

	@Test
	public void testNewinstance() {
		try {
			throw new EcBaseBusinessException("111", "订单号%s有误", "1212131");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
