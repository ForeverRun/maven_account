package com.atguigu.service.impl;

import com.atguigu.dao.IAccountDao;
import com.atguigu.domain.Account;
import com.atguigu.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * @author qmwh
 * @create 2019-05-30 09:44
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

   // @Autowired
    private IAccountDao accountDao;


    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }


    public List<Account> findAllAccount() {
            //2.执行
           return accountDao.findAll();
    }

    public Account findAccountById(Integer accountId) {

           return accountDao.findAccountByID(accountId);
    }

    public void saveAccount(Account account) {
            //2.执行
            accountDao.saveAccount(account);

    }

    public void updateAccount(Account account) {
            //2.执行
            accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer acccountId) {
            //2.执行
            accountDao.deleteAccount(acccountId);

    }

    public void transfer(String sourceName, String targetName, Float money) {
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
    }
}
