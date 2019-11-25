package com.neuedu.web.front;

import com.neuedu.commons.serverResponse;
import com.neuedu.pojo.Users;
import com.neuedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController  {
    @Autowired
    private UserService service;
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public serverResponse doLogin(String username, String password, HttpSession session){
        serverResponse rs = service.selectByUsernameAndPassword(username, password);
        session.setAttribute("user", rs.getData());
        return rs;
    }
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    public serverResponse doRegister(Users user){
        serverResponse rs = service.insertUser(user);
        return rs;
    }
}
