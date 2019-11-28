package com.neuedu.service.Impl;

import com.neuedu.commons.serverResponse;
import com.neuedu.dao.UsersDao;
import com.neuedu.pojo.Users;
import com.neuedu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UsersDao dao;

    @Override
    public Users selectById(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public serverResponse selectByUsernameAndPassword(String username, String password) {
        if (username == null || "".equals(username)){
            return serverResponse.serverFailed("用户名为空");
        }
        if (password == null || "".equals(password)){
            return serverResponse.serverFailed("密码为空");
        }
        Users user = dao.selectByUsernameAndPassword(username, password);
        if (user == null){
            return serverResponse.serverFailed("用户名或密码错误，请核对！");
        }else if(user.getType()!=1){
            return serverResponse.serverFailed("你不是管理员,权限不足");
        }else if(user.getStatus()!=1){
            return serverResponse.serverFailed("该账户已被冻结");
        }
        serverResponse rs = serverResponse.serverSuccess("登录成功", user);
        return rs;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public serverResponse insertUser(Users user) {
        if (user.getUsername() == null || "".equals(user.getUsername())){
            return serverResponse.serverFailed("用户名不能为空！");
        }
        if (user.getPassword() == null || "".equals(user.getPassword())){
            return serverResponse.serverFailed("密码不能为空！");
        }
        if (user.getEmail() == null || "".equals(user.getEmail())){
            return serverResponse.serverFailed("请输入邮箱！");
        }
        if (user.getPhone() == null || "".equals(user.getPhone())){
            return serverResponse.serverFailed("请输入手机号！");
        }
        //只是一种判断方法，还有根据用户名统计条数来判断的方法
        //运用了动态sql
        Users users = dao.selectByUsernameAndPassword(user.getUsername(), null);
        if(users != null){
            return serverResponse.serverFailed("用户名已存在");
        }
        int insert = dao.insert(user);
        return serverResponse.serverSuccess("注册成功");
    }
}

