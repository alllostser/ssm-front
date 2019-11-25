package com.neuedu.service;

import com.neuedu.commons.serverResponse;
import com.neuedu.pojo.Users;

public interface UserService {
    Users selectById(Integer id);
    serverResponse selectByUsernameAndPassword(String username, String password);
    serverResponse insertUser(Users user);

}
