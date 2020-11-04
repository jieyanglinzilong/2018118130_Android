package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
    /**
     *
     */
    @Autowired
    private UserDao userDao;
    @org.junit.Test
    public  void test()throws Exception {
        //1.读取配置文件

        //5.使用代理对象执行方法
        List<User> users = userDao.findUser();
        for(User user : users){

            System.out.println(user);
        }

        //6.释放资源

    }
    @org.junit.Test
    public void save(){
        User user=new User();
        user.setAddress("江苏南京");
        String day="1998-8-30";
        Date a=new Date(2018-1900, 9-1, 10);
        user.setId(1015);

        user.setBirthday(a);
        user.setName("秦枫");
        user.setSex("女");
        userDao.saveUser(user);



    }
    @org.junit.Test
    public void find(){

        List<User> users=userDao.findByName("周庆空");
        for(User user : users){

            System.out.println(user);
        }




    }
    @org.junit.Test
    public void findchoose1(){
        List<Integer> id=new ArrayList<Integer>();
        id.add(1010);
        id.add(1012);
        id.add(1018);
        List<User> users=userDao.findchoose(id);
        for(User user : users){

            System.out.println(user);
        }




    }
    /*@org.junit.Test
    public void find1(){
        List<cn.itcast.po.User> in=userDao.lianhe();
        for(cn.itcast.po.User user:in){
            System.out.println(user);}


    }*/
    /*
    @org.junit.Test
    public void find2(){
        List<cn.itcast.po.Users> in=userDao.con();
        for(cn.itcast.po.Users users:in){
            System.out.println(users);}


    }*/

}
