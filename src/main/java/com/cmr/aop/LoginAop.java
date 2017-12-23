package com.cmr.aop;

import com.cmr.entities.ConsoleResult;
import com.cmr.util.Login;
import com.cmr.util.Logout;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Component
@Aspect
@Slf4j
public class LoginAop {


	@Around("execution (* com.cmr.controller.*.*(..))")
	public Object loginAop(ProceedingJoinPoint pro) throws Throwable {

		MethodSignature methodSignature=(MethodSignature)pro.getSignature();
		String clazzName = pro.getSignature().getDeclaringType().getName();

		String name=methodSignature.getName();
		String[] parameters=methodSignature.getParameterNames();
		Class[] parameterTypes=methodSignature.getParameterTypes();
		log.info("class name:{}---->name:{}---->parameters:{}",clazzName,name,new ObjectMapper().writeValueAsString(parameters));
		Method method=pro.getTarget().getClass().getMethod(name,parameterTypes);
		Type type =method.getGenericReturnType();

		Login login=method.getAnnotation(Login.class);
		Logout logout=method.getAnnotation(Logout.class);
		Object proceed=null;
		if(null==login){
			if(null!=RequstContent.getSession().getAttribute("username")){
				proceed = pro.proceed();
				if(null!=logout){
					RequstContent.getSession().invalidate();
				}
			}else {
				if("class java.lang.String".equals(type.toString())&&!"login".equals(name)){
					proceed="redirect:/login";
				}else if("class com.cmr.entities.ConsoleResult".equals(type.toString())) {
					proceed=new ConsoleResult(ConsoleResult.ERROR_CODE,"用户未登录");
				}else {
					proceed = pro.proceed();
				}
			}
		}else{
			 proceed = pro.proceed();
			 ConsoleResult consoleResult=(ConsoleResult)proceed;
			 if(ConsoleResult.SUCCESS_CODE==consoleResult.getCode()){
				 RequstContent.getSession().setAttribute("username",RequstContent.getRequestLocal().getParameter("userName"));
			 }
		}

//		String mapper = null;
//		if (null != pro && null != pro.getSignature() && null != pro.getSignature().getDeclaringType()) {
//			String clazzName = pro.getSignature().getDeclaringType().getName();
//			logger.info("class:{}", clazzName);
//			mapper = clazzName.substring(clazzName.lastIndexOf(".") + 1, clazzName.length());
//			logger.info("mapper:{}", mapper);
//		}
//		DataSourceEnum datasource = DataSourceEnum.find(mapper);
//		MultipleDataSource.setDataSourceKey(datasource.getDatasourceName());
//		logger.info("【DataSourceAop-datasourceAop】AOP数据源切换,datasource：{}", datasource.getDatasourceName());
//		Object proceed = pro.proceed();



		return proceed;
	}
}
