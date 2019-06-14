package com.atguigu.service.impl;

import com.atguigu.dao.IAccountDao;
import com.atguigu.domain.Account;
import com.atguigu.service.IAccountService;
import com.atguigu.utils.TransitionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * @author qmwh
 * @create 2019-05-30 09:44
 */
@Service("accountService")
public class AccountServiceImpl_ implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private TransitionManager transitionManager;

//    public void setTransitionManager(TransitionManager transitionManager) {
//        this.transitionManager = transitionManager;
//    }

    //    public void setAccountDao(IAccountDao accountDao) {
//        this.accountDao = accountDao;
//    }


    public List<Account> findAllAccount() {
        try {
            //1.开启事务
            transitionManager.beginTransaction();
            //2.执行
            List<Account> list = accountDao.findAll();
            //3.提交
            transitionManager.commit();
            //4.得到返回结果
            return list;
        } catch (Exception e) {
            //5.回滚
            transitionManager.rollback();
            throw new RuntimeException();
        }finally {
            //6.关闭连接
            transitionManager.release();
        }

    }

    public Account findAccountById(Integer accountId) {
        try {
            //1.开启事务
            transitionManager.beginTransaction();
            //2.执行
            Account account = accountDao.findAccountByID(accountId);
            //3.提交
            transitionManager.commit();
            //4.得到返回结果
            return account;
        } catch (Exception e) {
            //5.回滚
            transitionManager.rollback();
            throw new RuntimeException();
        }finally {
            //6.关闭连接
            transitionManager.release();
        }
    }

    public void saveAccount(Account account) {
        try {
            //1.开启事务
            transitionManager.beginTransaction();
            //2.执行
            accountDao.saveAccount(account);
            //3.提交
            transitionManager.commit();
        } catch (Exception e) {
            //5.回滚
            transitionManager.rollback();
            throw new RuntimeException();
        }finally {
            //6.关闭连接
            transitionManager.release();
        }

    }

    public void updateAccount(Account account) {
        try {
            //1.开启事务
            transitionManager.beginTransaction();
            //2.执行
            accountDao.updateAccount(account);
            //3.提交
            transitionManager.commit();
        } catch (Exception e) {
            //5.回滚
            transitionManager.rollback();
            throw new RuntimeException();
        }finally {
            //6.关闭连接
            transitionManager.release();
        }

    }

    public void deleteAccount(Integer acccountId) {
        try {
            //1.开启事务
            transitionManager.beginTransaction();
            //2.执行
            accountDao.deleteAccount(acccountId);
            //3.提交
            transitionManager.commit();
        } catch (Exception e) {
            //5.回滚
            transitionManager.rollback();
            throw new RuntimeException();
        }finally {
            //6.关闭连接
            transitionManager.release();
        }

    }

    public void transfer(String sourceName, String targetName, Float money) {
        try {
            //1.开启事务
            transitionManager.beginTransaction();
            //2.执行
            //1. 找到转出账户
            Account sourceAccount = accountDao.findAccountByName(sourceName);
            //2.找到转入账户
            Account targetAccount = accountDao.findAccountByName(targetName);
            //3.转出账户减钱
            sourceAccount.setMoney(sourceAccount.getMoney() - money);
            //4.转入账户加钱
            targetAccount.setMoney(targetAccount.getMoney() + money);
            //5.更新转出账户
            accountDao.updateAccount(sourceAccount);
            //6.更新转入账户
            accountDao.updateAccount(targetAccount);
            int i = 1/0;
            //3.提交
            transitionManager.commit();
        } catch (Exception e) {
            //5.回滚
            transitionManager.rollback();
//            throw new RuntimeException();
            e.printStackTrace();
        }finally {
            //6.关闭连接
            transitionManager.release();
        }
    }
}
