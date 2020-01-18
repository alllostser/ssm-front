package com.neuedu.web.front;

import com.neuedu.commons.ResponseCode;
import com.neuedu.commons.serverResponse;
import com.neuedu.pojo.Users;
import com.neuedu.pojo.vo.UsersVo;
import com.neuedu.service.Impl.UserServiceImpl;
import com.neuedu.service.UserService;
import com.neuedu.utils.TimeUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    /**
     * 用户登录模块
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public serverResponse doLogin(String username, String password, HttpSession session) {
        serverResponse rs = service.selectByUsernameAndPassword(username, password);
        Users user = (Users) rs.getData();
        UsersVo usersVo = new UsersVo();
        session.setAttribute(ResponseCode.LOGIN_USER, user);
        //将user对象转换为vo对象返回
        if (rs.getData() != null) {
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
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public serverResponse doRegister(Users user) {
        serverResponse rs = service.insertUser(user);
        return rs;
    }

    /**
     * 检查参数是否有效
     *
     * @param value
     * @param type
     * @return
     */
    @RequestMapping(value = "/check_valid.do", method = RequestMethod.GET)
    public serverResponse checkValid(String type, String value) {
        serverResponse rs = service.checkValid(type, value);
        return rs;
    }

    /**
     * 获取登录用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/get_user_info.do", method = RequestMethod.GET)
    public serverResponse getUserInfo(HttpSession session) {
        Users user = (Users) session.getAttribute(ResponseCode.LOGIN_USER);
        if (user == null) {
            return serverResponse.serverFailed(ResponseCode.UserEnum.ERRORS.getCode(), ResponseCode.UserEnum.ERRORS.getMessage());
        }
        UsersVo usersVo = new UsersVo();
            usersVo.setId(user.getId());
            usersVo.setUsername(user.getUsername());
            usersVo.setNickname(user.getNickname());
            usersVo.setEmail(user.getEmail());
            usersVo.setPhone(user.getPhone());
            usersVo.setCreateDate(TimeUtils.dateToStr(user.getCreateDate()));
            usersVo.setUpdateDate(TimeUtils.dateToStr(user.getUpdateDate()));
            usersVo.setLoginNumber(user.getLoginNumber());
        return serverResponse.serverSuccess(usersVo);
    }

    /**
     * 获取当前登录用户的详细信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/get_inforamtion.do", method = RequestMethod.GET)
    public serverResponse getInforamtion(HttpSession session) {
        Users users = (Users) session.getAttribute(ResponseCode.LOGIN_USER);
        if (users == null) {
            return serverResponse.serverFailed(ResponseCode.UserEnum.ERRORS.getCode(), ResponseCode.UserEnum.ERRORS.getMessage());
        }
        return service.selectById(users.getId());
    }

    /**
     * 登录状态更新个人信息
     * @param session
     * @param users
     * @return
     */
    @RequestMapping(value = "/update_information.do", method = RequestMethod.GET)
    public serverResponse updateInformation(HttpSession session,Users users) {
        Users u = (Users) session.getAttribute(ResponseCode.LOGIN_USER);
        if (users == null) {
            return serverResponse.serverFailed(ResponseCode.UserEnum.ERRORS.getCode(), ResponseCode.UserEnum.ERRORS.getMessage());
        }
        users.setId(u.getId());
        users.setUsername(u.getUsername());
        serverResponse rs = service.updateInformation(users);
        return rs;
    }
    /**
     * 忘记密码
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/forget_get_question.do", method = RequestMethod.GET)
    public serverResponse forgetGetQuestion(String username) {
        return service.forgetGetQuestion(username);
    }





    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public serverResponse logOut(HttpSession session) {
        session.removeAttribute(ResponseCode.LOGIN_USER);
        return serverResponse.serverSuccess("注销成功");
    }

}
