package com.example.demo.dao;

import com.example.demo.domain.Info;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoDao {
    Info info(String name);
}
