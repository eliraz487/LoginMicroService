package com.example.demo.controller;

import com.example.demo.service.UsersService;
import com.example.demo.vo.UsersEntityUpdateVO;
import com.example.demo.vo.UsersEntityVO;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/User")
public class UserController {
    @PostMapping()
    public String createUser(@RequestBody UsersEntityVO users) {
        UsersService usersService = new UsersService();
        return usersService.createUser(users);

    }

    @PutMapping("/changeusername")
    public String editUser(@RequestBody UsersEntityUpdateVO userupdate) {

        UsersService usersService = new UsersService();
        return usersService.editUser(userupdate);
    }
    @PutMapping("/changepassword")
    public String changePassword(@RequestBody UsersEntityUpdateVO userupdate){
        UsersService usersService = new UsersService();
        return usersService.editPassword(userupdate);
    }


}
