package com.dubbo.provider.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.dubbo.provider.service.Nofify;

public class NofifyImpl implements Nofify {

	public Map<Integer, String> ret = new HashMap<Integer, String>();
    public Map<Integer, Throwable> errors = new HashMap<Integer, Throwable>();
	@Override
	public void onreturn(String msg) {
		// TODO Auto-generated method stub

		System.out.println("**************cosumer receive onreturn:" + msg+"****************");
//	    ret.put(msg);
	}

	@Override
	public void onthrow(Throwable ex, Integer id) {
		// TODO Auto-generated method stub
		System.out.println("**************cosumer invoke server has a error:" +ex.getMessage()+"****************");
		errors.put(id, ex);
	}

	@Override
	public void oninvoke(String msg, Integer id) {
		// TODO Auto-generated method stub
		System.out.println("**************cosumer begin invoke server:" + msg+"****************");
	}

}
