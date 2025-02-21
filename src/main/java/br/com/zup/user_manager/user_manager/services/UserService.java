package br.com.zup.user_manager.user_manager.services;

import br.com.zup.user_manager.user_manager.dtos.RegisterUserDTO;
import br.com.zup.user_manager.user_manager.models.UserModel;
import br.com.zup.user_manager.user_manager.dtos.UserLoginDTO;

import java.util.Map;

public interface UserService {

    Map<String, String> login(UserLoginDTO userLoginDTO);

    UserModel saveUser(UserModel user);

    void registerUser(RegisterUserDTO registerUserDTO);
}
