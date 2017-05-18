package com.dubbo.provider.service;

/**
 * @author fengchao
 * @data 2017年5月18日
 * 测试服务端参数回调
 * 共享服务接口为CallbackListener
 */
public interface CallbackService {

	void addListener(String key, CallbackListener listener);
	
}
