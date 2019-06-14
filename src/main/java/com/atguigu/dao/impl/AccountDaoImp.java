package com.atguigu.dao.impl;

import com.atguigu.dao.IAccountDao;
import com.atguigu.domain.Account;
import com.atguigu.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户的持久层实现类
 *
 * @author qmwh
 * @create 2019-05-30 09:03
 */
@Repository("accountDao")
public class AccountDaoImp implements IAccountDao {

    //@Autowired
    private QueryRunner runner;
   // @Autowired
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<Account> findAll() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account",
                    new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByID(Integer accountId) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account where id=?",
                    new BeanHandler<Account>(Account.class),accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"insert into account(name,money)values(?,?)",account.getName(),account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(Integer acccountId) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"delete from account where id=?",acccountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account findAccountByName(String name) {

        try {
            List<Account> account = runner.query(connectionUtils.getThreadConnection(),"select * from account where name=?",new BeanListHandler<Account>(Account.class),name);
            if(account == null || account.size() == 0){
                return null;
            }
            if (account.size() > 1) {
                throw new RuntimeException("结果不符合规定");
            }
            return account.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
