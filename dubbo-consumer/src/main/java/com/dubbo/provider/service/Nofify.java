package com.dubbo.provider.service;

public interface Nofify {

	 /**
	 * @author fengchao
	 * @data 2017年5月18日
	 * @注释 方法调用前触发
	 */
	public void oninvoke(String msg,Integer id);
	 /**
	 * @author fengchao
	 * @data 2017年5月18日
	 * @注释 方法返回后触发
	 */
	public void onreturn(String msg);
	 /**
	 * @author fengchao
	 * @data 2017年5月18日
	 * @注释 发生异常时触发
	 */
	public void onthrow(Throwable ex, Integer id);
}
