package com.example.demo.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsersEntityUpdateVO extends UsersEntityVO implements Serializable {

    private TryLoginEntityVO tryLogin;

    private String userName="";


    private String password="";


}
