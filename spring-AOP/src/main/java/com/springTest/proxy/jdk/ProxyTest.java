package com.springTest.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
//    jdk实现动态代理
public static void main(String[] args) {
    Target target = new Target();
//    增强对象
    Advice advice = new Advice();
    TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    advice.before();//前置增强
                    Object invoke = method.invoke(target, args);
                    advice.after();//后置增强
                    return invoke;
                }
            });
    int save = proxy.save();
    System.out.println(save);
}
}
