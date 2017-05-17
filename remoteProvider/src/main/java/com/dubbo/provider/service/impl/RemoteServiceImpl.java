package com.dubbo.provider.service.impl;

import com.dubbo.provider.service.RemoteService;

public class RemoteServiceImpl implements RemoteService {

	@Override
	public String sayDubbo(String name) {
		// TODO Auto-generated method stub
		return "*****************************dubbo say: welcome "+name+" I return you answer is hahahaha*********************************";
	}

	/* (non-Javadoc)
	 * @see com.dubbo.provider.service.RemoteService#testSyncCall(java.lang.String)
	 */
	@Override
	public String testSyncCall(String name) {
		// TODO Auto-generated method stub
		return "*****************************dubbo sync:welcome "+name+" test sync nio success****************************************";
	}

	@Override
	public String testSyncCallisReturn(String name) {
		// TODO Auto-generated method stub
		return "*****************************dubbo sync:welcome "+name+" test sync by return success****************************************";
	}

}
