package com.dubbo.provider.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dubbo.provider.service.CallbackListener;
import com.dubbo.provider.service.CallbackService;

public class CallbackServiceImpl implements CallbackService {

	private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<String, CallbackListener>();   //回调hook队列
	public CallbackServiceImpl() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						//遍历队列，发送消息
						for (Map.Entry<String, CallbackListener> entry : listeners.entrySet()) {
							try {
								entry.getValue().changed(getChanged(entry.getKey()));
							} catch (Throwable t) {
								listeners.remove(entry.getKey());
							}
						}
						Thread.sleep(3000); // 定时触发变更通知
					} catch (Throwable t) { // 防御容错
						t.printStackTrace();
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void addListener(String key, CallbackListener listener) {          //通过传进来的回调参数listener(回调hook钩子)，回调客户端代码
		// TODO Auto-generated method stub
		listeners.put(key, listener);               //加入需要回调监听的队列
		listener.changed(getChanged(key));          // 发送变更通知
	}

	private String getChanged(String key) {
		return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
