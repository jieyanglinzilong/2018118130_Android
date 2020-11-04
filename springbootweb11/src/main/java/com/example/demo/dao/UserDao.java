package com.example.demo.dao;




import com.example.demo.domain.User;
import com.example.demo.domain.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface  UserDao {
    List<User> findUser();
    void saveUser(User user);
    void deleteUser();
    List<User> findbyid(Integer id);
    List<User> findchoose(List<Integer> id);
    List<User>  findByName(String name);
    int getid(String name);
    List<Users> con();

}
