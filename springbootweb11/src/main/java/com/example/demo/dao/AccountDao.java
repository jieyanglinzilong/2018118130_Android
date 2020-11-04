package com.example.demo.dao;

import com.example.demo.domain.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface AccountDao {
    @Select("select*from account")
    List<Account> findall();
    @Insert("insert into account(uid,money)values(#{uid},#{money})")
    void saveAccount(Account account);
    @Delete("delete from account where name=#{name}")
    void delete(String name);
}
