package com.springTest.aop_anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//    切面
@Component("myAspect")
@Aspect//标注当前类是一个切面类
public class MyAspect {
//    抽取切点表达式
    @Pointcut("execution(* com.springTest.aop_anno.*.*(..))")
    public void myPoint(){}
//    前置增强
//    @Before("MyAspect.myPoint()")
    public void before(){
        System.out.println("前置增强");
    }
//    后置增强
//    @AfterReturning("myPoint()")
    public void afterReturning(){
        System.out.println("后置增强");
    }
//    环绕增强
    @Around("myPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕增强前");
//        执行接口中的方法
        Object proceed = pjp.proceed();
        System.out.println("环绕增强后");
        return proceed;
    }
//    异常抛出通知/增强
    @AfterThrowing("myPoint()")
    public void throwing(){
        System.out.println("异常抛出增强");
    }
//    最终增强
    @After("myPoint()")
    public void after(){
        System.out.println("最终增强");
    }
}
