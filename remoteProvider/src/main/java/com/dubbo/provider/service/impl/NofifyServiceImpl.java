package com.dubbo.provider.service.impl;

import com.dubbo.provider.service.NofifyService;

public class NofifyServiceImpl implements NofifyService{

	@Override
	public String get(int id) {
		// TODO Auto-generated method stub
		return "*********************server receive msg:===="+id+"******************************";
	}

}
