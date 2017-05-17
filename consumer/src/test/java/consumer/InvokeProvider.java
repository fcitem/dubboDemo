package consumer;

import java.util.concurrent.Future;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.GenericService;
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
		//客户端不需要服务端api接口调用
		GenericService barService = (GenericService) context.getBean("generService");
		Object result = barService.$invoke("sayDubbo", new String[] { "java.lang.String" }, new Object[] { "fc" });
		System.out.println(result);
	}
	/**
	 * @author fengchao
	 * @data 2017年5月17日
	 * @注释 测试绕过注册中心，点对点直连
	 */
	@Test
	public void testDubboP2P() {
		context=new ClassPathXmlApplicationContext("p2p/spring.xml");
		context.start();
		RemoteService demoservice=(RemoteService) context.getBean("demoService");
		System.out.println(demoservice.sayDubbo("fc"));
	}
	/**
	 * @author fengchao
	 * @data 2017年5月17日
	 * @注释 测试在客户端没有api接口的情况下的泛华引用，此种情况需要在客户端的<dubbo:reference>中加入generic="true"
	 */
	@Test
	public void testGeneeric(){
		context=new ClassPathXmlApplicationContext("Generatical/spring.xml");
		context.start();
		GenericService barService = (GenericService) context.getBean("demoService");
		Object result = barService.$invoke("sayDubbo", new String[] { "java.lang.String" }, new Object[] { "fc" });
		System.out.println(result);
	}

	/**
	 * @author fengchao
	 * @data 2017年5月17日
	 * @注释 客户端测试异步调用远程服务
	 */
	@Test
	public void testSync(){
		context=new ClassPathXmlApplicationContext("spring.xml");
		context.start();
		RemoteService demoservice=(RemoteService) context.getBean("demoService");
		demoservice.testSyncCall("fc");                    // 此调用会立即返回null
		demoservice.testSyncCallisReturn("hhj");
		Future<String> future=RpcContext.getContext().getFuture();   // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
		//测试得出，当有多个方法返回值类型一样时，future只能拿到最后一次异步调用的结果，所以这两次future拿到的结果会是一样的
		Future<String> future2=RpcContext.getContext().getFuture();   
		try {
			System.out.println(future.get()+" ================future");
			System.out.println(future2.get()+" ================future2");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @author fengchao
	 * @data 2017年5月17日
	 * @注释 测试客户端没有远程接口api的情况下的异步调用
	 */
	@Test
	public void testSyncNoApi(){
		context=new ClassPathXmlApplicationContext("spring.xml");
		context.start();
//		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
//		reference.setInterface("com.dubbo.provider.service.RemoteGeneraticService"); // 弱类型接口名 
//		reference.setVersion("1.0.0"); 
//		reference.setGeneric(true); // 声明为泛化接口 
//		reference.setAsync(true);   //异步调用
//		reference.setCache("lru");    //设置缓存策略
//		GenericService service=reference.get();   // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用 
		GenericService service = (GenericService) context.getBean("generService");
		service.$invoke("testSync", new String[] { "java.lang.String" }, new Object[] { "fc" });
		Future<String> future=RpcContext.getContext().getFuture();   // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
		try {
			System.out.println(future.get()+" ================future");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
