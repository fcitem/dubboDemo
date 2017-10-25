package com.dubbo.provider.service;

public interface RemoteGeneraticService {

	public String sayDubbo(String name);

	/**
	 * @author fengchao
	 * @data 2017年5月17日
	 * @注释 测试客户端在没有远程服务接口api的情况下的异步调用
	 */
	public String testSync(String name);
}
