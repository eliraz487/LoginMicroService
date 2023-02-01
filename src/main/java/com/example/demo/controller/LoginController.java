package com.example.demo.controller;

import com.example.demo.service.LoginService;
import com.example.demo.vo.TryLoginEntityVO;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/Login")
public class LoginController {


    @PostMapping("/tryLogin")
    public String loginTry(@RequestBody TryLoginEntityVO tryLoginEntityDTO) {
        LoginService login = new LoginService();

        try {
            return String.valueOf(login.loginTry(tryLoginEntityDTO));
        } catch (SQLException | IllegalAccessException | ClassNotFoundException e) {
            return e.toString();
        }

    }


}
