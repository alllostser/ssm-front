package com.neuedu.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Users {
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

    private Integer type;

    private Integer status;

    private String question;

    private String anwser;

    private Date createDate;

    private Date updateDate;

    private Integer loginNumber;

}