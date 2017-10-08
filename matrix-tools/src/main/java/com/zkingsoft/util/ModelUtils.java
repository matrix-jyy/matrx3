/**
 * 
 */
package com.zkingsoft.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;

import com.zkingsoft.anotations.Extend;

/**
 * @author wangsheng
 *
 */
public class ModelUtils {

	/**
	 * 比较新旧POJO对象，将修改过的值和字段名放入Map中
	 * 
	 * @param oldObj
	 * @param newObj
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String, Object> comparePojo2Map(Object oldObj, Object newObj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String, Object> map = new HashMap<>();
		Class<Object> oldClazz = (Class<Object>) oldObj.getClass();
		Field[] fields = oldClazz.getDeclaredFields();
		Class<Object> newClazz = (Class<Object>) newObj.getClass();
		
		for(Field field : fields){
			if(field.getAnnotation(Extend.class)!=null || Modifier.isStatic(field.getModifiers())){
				continue;
			}
			String getMethodName = "get"
                    + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
			Method oldM = (Method) oldClazz.getMethod(getMethodName);
			Method newM = (Method) newClazz.getMethod(getMethodName);
			if(newM.invoke(newObj)!=null&&!ObjectUtils.equals(oldM.invoke(oldObj), newM.invoke(newObj))){	//如果新旧的值不相等
				map.put(field.getName(), newM.invoke(newObj));
			}
		}
		return map;
	}
	
	public static void main(String[] args) {
		
	}

	/**
	 * 
	* @Description: 判断值是否被修改过
	* @author:姜友瑶
	* @param oldObj
	* @param newObj
	* @return    
	* 返回类型  boolean    
	* @date 2016年11月11日
	 */
	@SuppressWarnings({ "unchecked" })
	public static boolean isModified(Object oldObj, Object newObj) {
		if(oldObj==newObj){
			return true;
		}
		if(oldObj==null || newObj==null){
			return false;
		}
		try {
			Class<Object> oldClazz = (Class<Object>) oldObj.getClass();
			Field[] fields = oldClazz.getDeclaredFields();
			Class<Object> newClazz = (Class<Object>) newObj.getClass();
			//依次比较所有可以Modify的字段
			for(Field field : fields){
				if(field.getAnnotation(Extend.class)!=null){	//不是可以修改的字段
					continue;
				}
				String getMethodName = "get"
			            + field.getName().substring(0, 1).toUpperCase()
			            + field.getName().substring(1);
				Method oldM = (Method) oldClazz.getMethod(getMethodName);
				Method newM = (Method) newClazz.getMethod(getMethodName);
				if(isPrimitive(field) || String.class==field.getType() || Date.class==field.getType() || field.getType().isEnum()){
					//如果字段是基本类型，封装类型，以及String, Date, 枚举，则直接进行比较
					if(!ObjectUtils.equals(oldM.invoke(oldObj), newM.invoke(newObj))){	//如果新旧的值不相等
						return true;
					}
				}else if(field.getType().isAssignableFrom(List.class)){
					//如果是List
					if(isModifiedList((List<Object>)oldM.invoke(oldObj), (List<Object>)newM.invoke(newObj))){
						return true;
					}
				}else{
					//其他类型暂时不支持
					throw new RuntimeException("暂时不支持这种类型的修改判断："+field.getType());
				}
			}
			return false;
		} catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static boolean isPrimitive(Field field) throws IllegalArgumentException, IllegalAccessException, SecurityException {
		if(field.getType().isPrimitive()){
			return true;
		}
		try{
			if(((Class)field.getType().getField("TYPE").get(null)).isPrimitive()){
				return true;
			}
			return false;
		}catch(NoSuchFieldException e){
			return false;
		}
	}

	public static boolean isModifiedList(final Collection<Object> list1, final Collection<Object> list2) {
        if (list1 == list2) {
            return false;
        }
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return true;
        }
        Iterator<Object> it1 = list1.iterator();
        Iterator<Object> it2 = list2.iterator();
        Object obj1 = null;
        Object obj2 = null;
        while (it1.hasNext() && it2.hasNext()) {
            obj1 = it1.next();
            obj2 = it2.next();

            if (isModified(obj1, obj2)) {
                return true;
            }
        }
        return it1.hasNext() || it2.hasNext();
    }

	
}
