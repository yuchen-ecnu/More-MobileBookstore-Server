package com.framework.pool;

import java.util.ArrayList;
import java.util.List;
/**
 * 连接池管理类
 * @author kuangjun
 *
 */
public class Pool {
	private List<Poolable> loopObjects;
	private Class LoopObjectClass;
	/**
	 * 
	 * @param c 指定连接类型的类，它必须有空构造函数，并实现Loopable接口
	 */
	public Pool(Class c){
		LoopObjectClass=c;
		loopObjects=new ArrayList<Poolable>();
	}
	/**
	 * 获得连接池中的连接
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Poolable getLoopObject() throws InstantiationException, IllegalAccessException{
		for(Poolable l:loopObjects){
			if(!l.isUsed()){
				return l.open();
			}
		}
		Poolable l=(Poolable)this.LoopObjectClass.newInstance();
		this.loopObjects.add(l);
		return l.open();
	}
	
}
