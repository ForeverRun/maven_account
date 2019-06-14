package com.atguigu.proxy;

/**
 * 生产厂家的准则规范
 *
 * @author qmwh
 * @create 2019-05-31 16:39
 */
public interface IProducer {
    void sale(double money);
    void produce(double money);
}
