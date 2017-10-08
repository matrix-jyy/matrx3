package com.zkingsoft.util;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *导出excel的时候标示是否生成，该注解只能用在成员变量上
* @ClassName: ExcelAnnotation 
* @Description: TODO 
* @author 肖崇高  xiaochonggao@zkingsoft.com 
* @date 2016年8月3日 下午2:39:02 
*
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.FIELD) 
public @interface ExcelAnnotation {

	boolean checked()  default  false;
    
}
