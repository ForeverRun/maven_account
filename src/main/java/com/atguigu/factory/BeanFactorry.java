package com.atguigu.factory;

import com.atguigu.service.IAccountService;
import com.atguigu.utils.TransitionManager;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 *
 * @author qmwh
 * @create 2019-05-31 22:17
 */
public class BeanFactorry {
   // @Autowired
    private IAccountService accountService;
    //@Autowired
    private TransitionManager txManager;

    public void setTxManager(TransitionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Bean(name = "proxyAccountService")
    public IAccountService getAccountService(){
        //IAccountService service =
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if("test".equals(method.getName())){
                            return method.invoke(accountService,args);
                        }
                        Object rtValue = null;
                        try {
                            //1.开启事务
                            txManager.beginTransaction();
                            System.out.println("这是"+args);
                            //2.执行操作
                            rtValue = method.invoke(accountService, args);
                            System.out.println("这是222222"+args);
                            //3.提交事务
                            txManager.commit();
                            System.out.println("这是333333333"+args);
                            //4.返回结果
                            return rtValue;
                        } catch (Exception e) {
                            //5.回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //6.释放连接
                            txManager.release();
                        }
                    }
                });
       // return service;
    }
}
