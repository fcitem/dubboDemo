package consumer;

import java.io.IOException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.dubbo.provider.service.CallbackListener;
import com.dubbo.provider.service.CallbackService;
import com.dubbo.provider.service.RemoteService;
import com.dubbo.provider.service.impl.NofifyImpl;

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
		//不通过spring配置文件调用，不完整，后面完善
//		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
//		reference.setInterface("com.dubbo.provider.service.RemoteGeneraticService"); // 弱类型接口名 
//		reference.setVersion("1.0.0"); 
//		reference.setGeneric(true); // 声明为泛化接口 
//		reference.setAsync(true);   //异步调用
//		reference.setCache("lru");    //设置缓存策略
//		List<MethodConfig> methods = new ArrayList<MethodConfig>();
//		MethodConfig method = new MethodConfig();
//		method.setName("testSync");
//		method.setTimeout(10000);
//		method.setRetries(0);
//		method.setAsync(true);          //异步调用
//		methods.add(method);
//		reference.setMethods(methods);    //设置方法级配置
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
	/**
	 * @author fengchao
	 * @data 2017年5月18日
	 * @注释 测试服务器端根据回调参数回调客户端代码
	 */
	@Test
	public void testCallback(){
		context=new ClassPathXmlApplicationContext("spring.xml");
		context.start();
		CallbackService service=(CallbackService) context.getBean("callbackService");
		service.addListener("test.bar",new CallbackListener() {
			
			@Override
			public void changed(String msg) {
				System.out.println("*********************receive server call back msg:"+msg+"****************************");
				
			}
		});
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author fengchao
	 * @throws InterruptedException 
	 * @data 2017年5月18日
	 * @注释 测试基于dubbo的事件通知效果
	 */
	@Test
	public void testNofify() throws InterruptedException{
		context=new ClassPathXmlApplicationContext("spring.xml");
		context.start();
		int requestId=2;
		GenericService service = (GenericService) context.getBean("nofifyService");
		NofifyImpl nofify=(NofifyImpl) context.getBean("nofifyImpl");
		Object ret=service.$invoke("get",new String[] {"java.lang.Integer"}, new Object[]{requestId});   //调用远程服务
		Assert.assertEquals(null, ret);
		for (int i = 0; i < 10; i++) {
		    if (!nofify.ret.containsKey(requestId)) {
		        Thread.sleep(200);
		    } else {
		        break;
		    }
		}
	}
}
