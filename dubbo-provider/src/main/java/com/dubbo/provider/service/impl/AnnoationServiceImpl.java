package com.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.provider.service.AnnoationService;

/**通过dubbo注解暴露服务
 * @author fengchao
 *
 */
@Service(version="1.0.0")
public class AnnoationServiceImpl implements AnnoationService{

	@Override
	public String testAnnoationService(String msg) {
		return "Annoation Service provider:"+msg;
	}
}
