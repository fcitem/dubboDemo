package consumer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.provider.service.RemoteService;

public class InvokeProvider {

	ClassPathXmlApplicationContext context;
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
		RemoteService demoservice=(RemoteService) context.getBean("demoService");
		System.out.println(demoservice.sayDubbo("fc"));
	}

}
