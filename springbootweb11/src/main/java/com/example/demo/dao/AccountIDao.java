package com.example.demo.dao;


import com.example.demo.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AccountIDao {
    List<Account> find();
}
