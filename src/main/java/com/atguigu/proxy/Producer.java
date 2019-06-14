package com.atguigu.proxy;

/**
 * 生产厂家
 *
 * @author qmwh
 * @create 2019-05-31 16:26
 */
public class Producer implements IProducer {

    /**
     * 生产的方法
     */
    public void produce(double money){
        System.out.println("生产了一件商品，卖了"+money+"元");
    }

    /**
     * 售后的方法
     */
    public void sale(double money){
        System.out.println("进行售后，需要"+money+"元");
    }
}
