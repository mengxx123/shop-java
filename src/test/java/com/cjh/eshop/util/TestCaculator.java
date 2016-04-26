package com.cjh.eshop.util;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cjh.eshop.util.Caculator;

public class TestCaculator {
	
	@Before
	public void setUp() {
		System.out.println("before");
	}
	
	@Test
	public void testAdd() {
		int result = Caculator.add(2, 3);
		assertEquals("加法有问题", 5, result);
	}
	
	@Test
	public void testMinus() {
		int result = Caculator.minus(8, 3);
		assertEquals("减法有问题", 5, result);
	}
	
	@Test
	public void testDivide() {
		int result = Caculator.divide(15, 3);
		assertEquals("除法有问题", 5, result);
	}
	
	@Test(expected = ArithmeticException.class)
	public void testDivideException() {
		int result = Caculator.divide(15, 0);
		assertEquals("除法有问题", 15, result);
	}

	@After
	public void tearDown() {
		System.out.println("after");
	}
	
	/*@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
	}*/
}
