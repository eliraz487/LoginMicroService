package com.example.demo.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class UsersEntityVO implements Serializable {
    private String userName="";

    private String password="";

    private String email="";

    private String countryCode="";



}
