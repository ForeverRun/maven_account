package com.atguigu.test;

import com.atguigu.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用Junit单元测试：测试我们的配置
 *
 * @author qmwh
 * @create 2019-05-30 10:04
 */
@ComponentScan(value = "com.atguigu")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService as;

    @Test
    public void testTransfer(){
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
//        IAccountService as = applicationContext.getBean("accountService", AccountServiceImpl.class);
        as.transfer("aaa","bbb",200f);
    }
}
