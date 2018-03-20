package com.dq.easy.cloud.model.date.utils;

import java.util.Date;

import org.junit.Test;

import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;

public class DqDateFormatUtilsTest {

	@Test
	public void testFormat(){
		Date date = null;
		String str = DqDateFormatUtils.format(date, null);
		System.out.println("时间：" + str);
	}
	
}
