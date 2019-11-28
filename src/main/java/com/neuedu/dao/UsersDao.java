package com.neuedu.dao;

import com.neuedu.pojo.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersDao {
    //登录
    Users selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int insert(Users record);  //注册

    int selectByUsernameOrEmailOrPhone(@Param("type") String type,@Param("value") String value);

    Users selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);


    int insertSelective(Users record);


    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}