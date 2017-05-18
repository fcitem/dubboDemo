package com.dubbo.provider.service;

/**
 * @author fengchao
 * @data 2017年5月18日
 * 测试服务端参数回调
 * 共享服务接口 CallbackListener
 * 既然是共享，服务端和客户端都要有
 */
public interface CallbackService {

	public void addListener(String key, CallbackListener listener);
	
}
