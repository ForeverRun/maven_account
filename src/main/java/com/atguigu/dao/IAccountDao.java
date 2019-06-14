package com.atguigu.dao;

import com.atguigu.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 *
 * @author qmwh
 * @create 2019-05-30 08:57
 */
public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 查询一个
     * @return
     */
    Account findAccountByID(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param acccountId
     */
    void deleteAccount(Integer acccountId);

    /**
     * 通过账户名寻找
     * @param name
     * @return
     */
    Account findAccountByName(String name);
}
