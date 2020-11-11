package com.springTest.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
//    切面
//    前置增强
    public void before(){
        System.out.println("前置增强");
    }
//    后置增强
    public void afterReturning(){
        System.out.println("后置增强");
    }
//    环绕增强
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕增强前");
//        执行接口中的方法
        Object proceed = pjp.proceed();
        System.out.println("环绕增强后");
        return proceed;
    }
//    异常抛出通知/增强
    public void throwing(){
        System.out.println("异常抛出增强");
    }
//    最终增强
    public void after(){
        System.out.println("最终增强");
    }
}
