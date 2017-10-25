package com.dubbo.provider.service;

public interface RemoteService {

	public String sayDubbo(String name);
	public String testSyncCall(String name);
	public String testSyncCallisReturn(String name);
}
