package com.neuedu.service;

import com.neuedu.commons.serverResponse;
import com.neuedu.pojo.Users;

public interface UserService {
    serverResponse selectById(Integer id);
    serverResponse selectByUsernameAndPassword(String username, String password);
    serverResponse insertUser(Users user);

    serverResponse checkValid(String str, String type);

    serverResponse updateInformation(Users users);
}
