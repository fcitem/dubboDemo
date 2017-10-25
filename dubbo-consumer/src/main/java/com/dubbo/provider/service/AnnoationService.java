package com.dubbo.provider.service;

import org.springframework.stereotype.Component;

/**通过dubbo注解暴露服务
 * @author fengchao
 *
 */
@Component
public interface AnnoationService {

	public String testAnnoationService(String msg);
}
