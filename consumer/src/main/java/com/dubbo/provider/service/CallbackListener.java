package com.dubbo.provider.service;

/**
 * @author fengchao
 * @data 2017年5月18日
 * 测试服务端参数回调
 * 共享服务接口 CallbackListener
 */
public interface CallbackListener {

	void changed(String msg);
}
