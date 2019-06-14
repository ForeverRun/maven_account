package com.atguigu.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 购买电脑的人
 *
 * @author qmwh
 * @create 2019-05-31 16:41
 */
public class Customer {

    public static void main(String[] args) {
        final Producer pro = new Producer();

        Producer producer = (Producer) Enhancer.create(pro.getClass(), new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
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
        producer.sale(12000);
    }
}
