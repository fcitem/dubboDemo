package com.dubbo.provider.service.impl;

import com.dubbo.provider.service.RemoteGeneraticService;

public class RemoteGeneraticServiceImpl implements RemoteGeneraticService {

	@Override
	public String sayDubbo(String name) {
	    return "*****************************dubbo say: welcome "+name+" I return you answer is Generatic*********************************";
	}
	@Override
	public String testSync(String name) {
	    return "*****************************dubbo no api async: welcome "+name+" I return you answer is Generatic*********************************";
	}

}
