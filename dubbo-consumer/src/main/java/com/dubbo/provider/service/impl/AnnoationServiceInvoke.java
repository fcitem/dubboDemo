package com.dubbo.provider.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.provider.service.AnnoationService;

/**注解方式调用服务
 * @author fengchao
 *
 */
@Component
public class AnnoationServiceInvoke{

	/**
	 * 注入服务
	 * filter 加入过滤拦截，若缺省可通过dubbo:consumer加入所有调用的拦截
	 */
	@Reference(version="1.0.0",filter="testFilter")
	AnnoationService service;
	
	public void testAnnoationService(){
		String response=service.testAnnoationService("hello fc");
		System.out.println(response);
	}
	
}
