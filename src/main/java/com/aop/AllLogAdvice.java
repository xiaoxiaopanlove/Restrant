package com.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AllLogAdvice {
	private Logger logger = Logger.getLogger(AllLogAdvice.class);
	@Pointcut("execution(* com.service.*.*(..))")
	private void allMethod(){}
	/**
	 * 配置前置通知
	 * @param joinpoint
	 */
	@Before("allMethod()")
	public void myBeforeAdvice(JoinPoint joinpoint){
		//获取类名
		String targetClassName=joinpoint.getTarget().getClass().getName();
		//获取调用的方法名
		String targetMethodName=joinpoint.getSignature().getName();
		//生成日志信息
		String logInfoText="前置通知："+targetClassName+"类的"+targetMethodName+"方法开始执行";
		//将日志信息写入日志文件
		logger.info(logInfoText);
	}
	/**
	 * 配置后置通知
	 * @param joinpoint
	 */
	@AfterReturning("allMethod()")
	public void myAfterAdvice(JoinPoint joinpoint){
		//获取类名
		String targetClassName=joinpoint.getTarget().getClass().getName();
		//获取调用的方法名
		String targetMethodName=joinpoint.getSignature().getName();
		//生成日志信息
		String logInfoText="后置通知："+targetClassName+"类的"+targetMethodName+"方法开始执行";
		//将日志信息写入日志文件
		logger.info(logInfoText);
	}	
	/**
	 * 配置异常通知
	 * @param joinpoint
	 */
	@AfterThrowing(pointcut="allMethod()",throwing="e")
	public void myThrowingAdvice(JoinPoint joinpoint){
		//获取类名
		String targetClassName=joinpoint.getTarget().getClass().getName();
		//获取调用的方法名
		String targetMethodName=joinpoint.getSignature().getName();
		//生成日志信息
		String logInfoText="异常通知：执行"+targetClassName+"类的"+targetMethodName+"方法时发生异常";
		//将日志信息写入日志文件
		logger.info(logInfoText);
	}
	/**
	 * 环绕配置通知
	 * @param joinpoint
	 * @throws Throwable
	 */
	@Around("allMethod()")
	public Object myAroundAdvice(ProceedingJoinPoint joinpoint) throws Throwable{
		long beginTime=System.currentTimeMillis();
		Object obj=joinpoint.proceed();
		long endTime=System.currentTimeMillis();
		//获取调用的方法名
		String targetMethodName=joinpoint.getSignature().getName();
		//生成日志信息
		String logInfoText="环绕通知："+targetMethodName+"方法执行前时间"+beginTime+"毫秒，方法执行后时间"+endTime+"毫秒";
		logger.info(logInfoText);
		return obj;
	}
}
