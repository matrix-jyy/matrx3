package com.zkingsoft.syslog;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.model.sys.SysUsers;


/**
 * 
* @Description: 系统日志AOP
* @author:姜友瑶 
* @date 2016年8月28日
 */
@Aspect
public class LogAspect {


	@Autowired
	private SysLogService sysLogServices;
	

	/**
	 * 添加业务逻辑方法切入点
	 */
	@Pointcut("execution(* com.zkingsoft.dao.*.*.insert(..))")
	public void insertCell() {
	}

	/**
	 * 修改业务逻辑方法切入点
	 */
	@Pointcut("execution(* com.zkingsoft.dao.*.*.update(..))")
	public void updateCell() {
	}

	/**
	 * 删除业务逻辑方法切入点
	 */
	@Pointcut("execution(* com.zkingsoft.dao.*.*.deleteById(..))")
	public void deleteCell() {
	}
	
	/**
	 * 批量删除业务逻辑方法切入点
	 */
	@Pointcut("execution(* com.zkingsoft.dao.*.*.deleteByIds(..))")
	public void deletesCell() {
	}

	/**
	 * 添加操作日志(后置通知)
	 * 
	 * @param joinPoint
	 * @param rtv
	 */
	@AfterReturning(value = "insertCell()", argNames = "rtv", returning = "rtv")
	public void insertLog(JoinPoint joinPoint, Object rtv) throws Throwable {
		doLog(joinPoint);
	}

	/**
	 * 管理员修改操作日志(后置通知)
	 * @param joinPoint
	 * @param rtv
	 * @throws Throwable
	 */
	@AfterReturning(value = "updateCell()", argNames = "rtv", returning = "rtv")
	public void updateLog(JoinPoint joinPoint, Object rtv) throws Throwable {
		doLog(joinPoint);
	}

	/**
	 * 删除操作
	 * 
	 * @param joinPoint
	 * @param rtv
	 */
	@AfterReturning(value = "deleteCell()", argNames = "rtv", returning = "rtv")
	public void deleteLog(JoinPoint joinPoint, Object rtv) throws Throwable {
		doLog(joinPoint);
	}

	/**
	 * 批量删除操作
	 * 
	 * @param joinPoint
	 * @param rtv
	 */
	@AfterReturning(value = "deletesCell()", argNames = "rtv", returning = "rtv")
	public void deletesLog(JoinPoint joinPoint, Object rtv) throws Throwable {
		doLog(joinPoint);
	}
	
	
	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
	 * 
	 * @param args
	 * @param mName
	 * @return
	 */
	public String optionContent(Object[] args, String mName) {
		if (args == null) {
			return null;
		}
		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		// 遍历参数对象
		for (Object info : args) {
			// 获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("值:"+info.toString()+" ");
		}
		return rs.toString();
	}
	/**
	 * 获取session
	 * @author Matrix-J 
	 * @return HttpSession
	 */
	public HttpSession getSession() {
		if( RequestContextHolder.getRequestAttributes()!=null){
		   return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		}
		else return null;
	}
	
	/**
	 * 获取session
	 * @author Matrix-J 
	 * @return HttpSession
	 */
	public HttpServletRequest getRequest() {
		if(RequestContextHolder.getRequestAttributes()!=null){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}
		else return null;
	}
	
	

	private void doLog(JoinPoint joinPoint) {
		if(getSession()==null){
			return;
		}
		SysLog log = new SysLog();
		Object userObj=getSession().getAttribute(SystemConstance.LOGIN_KEY);
		SysUsers user=(SysUsers) userObj;
		if (userObj != null) {// 没有用户
			log.setLogUserName(user.getSuName());
			log.setUserId(user.getSuId());
		}
		// 判断参数
		if (joinPoint.getArgs() == null) {// 没有参数
			return;
		}
		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		// 获取操作内容
		log.setLogOperation(methodName);
		String opContent = optionContent(joinPoint.getArgs(), methodName);
		log.setLogIp(getRequest().getRemoteAddr());
		log.setLogCreatedate(new Date());
		log.setLogContent(opContent);
		sysLogServices.add(log);
	}

}