package com.springTest.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {
//    cglib实现动态代理
    public static void main(String[] args) {
//        目标对象
        Target target = new Target();
//        增强对象
        Advice advice = new Advice();
//        返回值 动态生成的代理对象 基于cglib
//        创建增强器
        Enhancer enhancer = new Enhancer();
//        设置父类(目标)
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                调用前置增强
                advice.before();
//                执行目标方法
                Object invoke = method.invoke(target,objects);
//                调用后置增强
                advice.after();;
                return invoke;
            }
        });
//        创建代理对象
        Target proxy = (Target) enhancer.create();
        int save = proxy.save();
        System.out.println(save);
    }
}
