package com.dubbo.provider.service;

/**
 * @author fengchao
 * @data 2017年5月17日
 * 测试客户端在没有接口api的情况下的泛化引用
 */
public interface RemoteService {

	public String sayDubbo(String name);
	
    /**
     * @author fengchao
     * @data 2017年5月17日
     * @注释 测试客户端异步调用
     */
    public String testSyncCall(String name);
    
    /**
     * @author fengchao
     * @data 2017年5月17日
     * @注释 测试客户端多个future异步调用取得的结果是否根据返回值类型匹配相应的future
     */
    public String testSyncCallisReturn(String name);
}
