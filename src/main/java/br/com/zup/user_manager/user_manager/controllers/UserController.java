package br.com.zup.user_manager.user_manager.controllers;

import br.com.zup.user_manager.user_manager.dtos.RegisterUserDTO;
import br.com.zup.user_manager.user_manager.models.UserModel;
import br.com.zup.user_manager.user_manager.dtos.UserLoginDTO;
import br.com.zup.user_manager.user_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void registerUser(@RequestBody RegisterUserDTO registerUserDTO){

       userService.registerUser(registerUserDTO);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }
}