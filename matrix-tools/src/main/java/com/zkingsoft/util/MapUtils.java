package com.zkingsoft.util;

import java.util.HashMap;

/**
 * File: MapUtils.java<br/>
 * Description: <br/>
 *
 * Copyright: Copyright (c) 2014 ekeyrun.com<br/>
 * Company: EKEYRUN,Inc.<br/>
 *
 * @author wangsheng
 * @date 2014年11月3日
 * @version 1.0
 */
public class MapUtils {

	public static ServiceMap<String, Object> buildDAOMap(){
		ServiceMap<String, Object> em = new ServiceMap<String, Object>();
		return em;
	}
	
	public static class ServiceMap<K, V> extends HashMap<K, V>{
		
		private static final long serialVersionUID = -3298666013018650546L;

		public ServiceMap<K, V> of(K k, V v){
			super.put(k, v);
			return this;
		}
		
	}
}
