package com.cjh.eshop.util;

import org.junit.Assert;
import org.junit.Test;

import com.cjh.eshop.util.Md5Util;

public class Md5UtilTest {
	
	@Test
	public void testEncode() {
		System.out.println(Md5Util.encode("123"));
		Assert.assertEquals("f30aa7a662c728b7407c54ae6bfd27d1", Md5Util.encode("hello123"));
	}
}
