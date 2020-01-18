package com.neuedu.service.Impl;

import com.neuedu.commons.ResponseCode;
import com.neuedu.commons.serverResponse;
import com.neuedu.dao.UsersDao;
import com.neuedu.pojo.Users;
import com.neuedu.service.UserService;
import com.neuedu.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UsersDao dao;

    /**
     * 取当前登录用户的详细信息
     * @param id
     * @return
     */
    @Override
    public serverResponse selectById(Integer id) {
        if (id == null || "".equals(id)){
            return serverResponse.serverFailed("未登录或未知错误");
        }
        Users users = dao.selectByPrimaryKey(id);
        if (users.getStatus() == 0){
            return serverResponse.serverFailed("该账号已被冻结");
        }
        users.setPassword(null);
        return serverResponse.serverSuccess(users);
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
        String md5Code = MD5Utils.getMD5Code(password);
        Users user = dao.selectByUsernameAndPassword(username, md5Code);
        if (user == null){
            return serverResponse.serverFailed("用户名或密码错误，请核对！");
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
        //校验用户是否存在
        int i = dao.selectByUsernameOrEmailOrPhone("username", user.getUsername());
        if(i>0){
            return serverResponse.serverFailed("用户名已存在");
        }
        String md5Code = MD5Utils.getMD5Code(user.getPassword());
        user.setPassword(md5Code);
        int insert = dao.insert(user);
        return serverResponse.serverSuccess("注册成功");
    }

    /**
     * 检查参数是否有效
     * @param value
     * @param type
     * @return
     */
    @Override
    public serverResponse checkValid(String type, String value) {
        if (value == null || "".equals(value)){
            return serverResponse.serverFailed("参数不能为空");
        }
        if (type == null || "".equals(type)){
            return serverResponse.serverFailed("参数类型为空");
        }
        int i = dao.selectByUsernameOrEmailOrPhone(type, value);
        if (i>0 && "username".equals(type)){
            return serverResponse.serverFailed("用户名已存在");
        }
        if (i>0 && "email".equals(type)){
            return serverResponse.serverFailed("该邮箱已被使用");
        }
        if (i>0 && "phone".equals(type)){
            return serverResponse.serverFailed("该手机号已被使用");
        }
        return serverResponse.serverSuccess("可以使用");
    }

    /**
     * 登录状态更新个人信息
     * @param users
     * @return
     */
    @Override
    public serverResponse updateInformation(Users users) {
        int i = dao.updateByPrimaryKeySelective(users);
        if (i<=0){
            return serverResponse.serverFailed("更新失败");
        }
        return serverResponse.serverSuccess("更新成功");
    }

    @Override
    public serverResponse forgetGetQuestion(String username) {
        if (username == null || "".equals(username)){
            return serverResponse.serverFailed("参数不能为空");
        }
        int i = dao.selectByUsernameOrEmailOrPhone("username", username);
        if (i<=0){
            return serverResponse.serverFailed("用户名不存在");
        }
        String s = dao.selectByUsername(username);
        return serverResponse.serverSuccess(s);
    }
}

