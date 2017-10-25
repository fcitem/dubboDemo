package com.remoteProvider.test;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartProvider {

	ClassPathXmlApplicationContext context=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		context=new ClassPathXmlApplicationContext("spring.xml");
		context.start();
		try {
			System.in.read();             //阻塞方式，让当前服务当代
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author fengchao
	 * @data 2017年5月17日
	 * @注释 测试点对点直连
	 */
	@Test
	public void testP2P() {
		context=new ClassPathXmlApplicationContext("p2p/spring.xml");
		context.start();
		try {
			System.in.read();             //阻塞方式，让当前服务当代
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author fengchao
	 * @data 2017年5月17日
	 * @注释 启动测试客户端泛化引用
	 */
	@Test
	public void testGeneratic() {
		context=new ClassPathXmlApplicationContext("Generatical/spring.xml");
		context.start();
		try {
			System.in.read();             //阻塞方式，让当前服务当代
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
