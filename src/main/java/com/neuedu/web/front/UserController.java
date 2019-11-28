package com.neuedu.web.front;

import com.neuedu.commons.serverResponse;
import com.neuedu.pojo.Users;
import com.neuedu.pojo.vo.UsersVo;
import com.neuedu.service.UserService;
import com.neuedu.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController  {
    @Autowired
    private UserService service;

    /**
     * 用户登录模块
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public serverResponse doLogin(String username, String password, HttpSession session){
        serverResponse rs = service.selectByUsernameAndPassword(username, password);
        Users user = (Users) rs.getData();
        UsersVo usersVo = new UsersVo();
        session.setAttribute("user", user);
        //将user对象转换为vo对象返回
        if (rs.getData()!=null){
            usersVo.setId(user.getId());
            usersVo.setUsername(user.getUsername());
            usersVo.setNickname(user.getNickname());
            usersVo.setEmail(user.getEmail());
            usersVo.setPhone(user.getPhone());
            usersVo.setCreateDate(TimeUtils.dateToStr(user.getCreateDate()));
            usersVo.setUpdateDate(TimeUtils.dateToStr(user.getUpdateDate()));
            usersVo.setLoginNumber(user.getLoginNumber());
            rs.setData(usersVo);
            return rs;
        }
        return rs;
    }
    /**
     * 用户注册模块
     * @param user
     * @return
     */
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    public serverResponse doRegister(Users user){
        serverResponse rs = service.insertUser(user);
        return rs;
    }

}
