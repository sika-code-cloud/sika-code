package com.easy.cloud.core.date.utils;

import java.util.Date;

import org.junit.Test;

import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;

public class DqDateFormatUtilsTest {

	@Test
	public void testFormat(){
		Date date = null;
		String str = EcDateFormatUtils.format(date, null);
		System.out.println("时间：" + str);
	}
	
}
