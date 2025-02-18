package br.com.zup.user_manager.user_manager.services;

import br.com.zup.user_manager.user_manager.models.UserModel;
import br.com.zup.user_manager.user_manager.repositories.UserLoginDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, String> login(UserLoginDTO userLoginDTO);

    UserModel saveUser(UserModel user);
}
