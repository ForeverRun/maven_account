package com.atguigu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 购买电脑的人
 *
 * @author qmwh
 * @create 2019-05-31 16:41
 */
public class Customer {

    public static void main(String[] args) {
        final Producer pro = new Producer();

        IProducer ipo = (IProducer) Proxy.newProxyInstance(pro.getClass().getClassLoader(), pro.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //进行方法的增强
                        Object returnValue = null;
                        System.out.println("这个"+args[0]);
                        //代理商从中赚取差价
                        Double money = (Double)args[0];
                        //double money = 20000;
                        //2.判断当前方法是不是销售
                        if("sale".equals(method.getName())) {
                            returnValue = method.invoke(pro, money*0.8);
                        }
                        return returnValue;
                    }
                });

        ipo.sale(10000);
    }
}
