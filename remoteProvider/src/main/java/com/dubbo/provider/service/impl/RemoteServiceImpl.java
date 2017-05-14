package com.dubbo.provider.service.impl;

import com.dubbo.provider.service.RemoteService;

public class RemoteServiceImpl implements RemoteService {

	@Override
	public String sayDubbo(String name) {
		// TODO Auto-generated method stub
		return "dubbo say: welcome "+name+" I already return you answer";
	}

}
