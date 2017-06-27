package com.framework.pool;

public interface Poolable {
	/**
	 * 是否被使用
	 * @return
	 */
	public boolean isUsed();
	/**
	 * 打开,调用该方法后isUsed()返回ture;
	 */
	public Poolable open();
	/**
	 * 关闭,调用该方法后isUsed()返回false;
	 */
	public void close();
}
