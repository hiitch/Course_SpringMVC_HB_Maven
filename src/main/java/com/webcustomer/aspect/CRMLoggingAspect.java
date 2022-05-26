package com.webcustomer.aspect;

import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private static final Logger myLogger = Logger.getLogger(CRMLoggingAspect.class.getName());
	
	@Pointcut("execution(* com.webcustomer.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.webcustomer.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("execution(* com.webcustomer.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("forControllerPackage() || forDAOPackage() || forServicePackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("==> Executing @Before, calling method : " + method);
		
		Object[] args = joinPoint.getArgs();
		
		Stream.of(args).forEach(arg -> myLogger.info("==> argument : " + arg));
	}
	
	@AfterReturning(pointcut="forAppFlow()", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("==> Executing @AfterReturning, calling method : " + method);
		
		myLogger.info("==> result : " + result);
	}
}
