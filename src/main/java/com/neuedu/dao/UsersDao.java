package com.neuedu.dao;

import com.neuedu.pojo.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersDao {
    Users selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}