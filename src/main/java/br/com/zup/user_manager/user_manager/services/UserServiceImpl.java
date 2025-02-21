package br.com.zup.user_manager.user_manager.services;

import br.com.zup.user_manager.user_manager.dtos.RegisterUserDTO;
import br.com.zup.user_manager.user_manager.dtos.UserLoginDTO;
import br.com.zup.user_manager.user_manager.models.Role;
import br.com.zup.user_manager.user_manager.models.UserModel;
import br.com.zup.user_manager.user_manager.repositories.RoleRepository;
import br.com.zup.user_manager.user_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserModel saveUser(UserModel user) {
        String passwordEncoder = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder);
        return userRepository.save(user);
    }

    public Map<String, String> login(UserLoginDTO userLoginDTO) {
        Optional<UserModel> userOptional = userRepository.findByUserName(userLoginDTO.getUserName());
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            if (bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
                return Map.of("mensagem:", "usuario logado");
            }
            return Map.of("mensagem:", "senha invalida");
        }
        return Map.of("mensagem:", "userName invalido");
    }

    public void registerUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByUserName(registerUserDTO.getUserName())) {
            throw new RuntimeException("Unprocess Entity");
        }
        UserModel user = new UserModel();
        user.setUserName(registerUserDTO.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(registerUserDTO.getPassword()));
        Set<Role> roles = registerUserDTO.getRoles().stream()
                .map(rolesEnum -> new Role(rolesEnum.name()))
                .collect(Collectors.toSet());
        roleRepository.saveAll(roles);

        user.setRoles(roles);
        userRepository.save(user);
    }
}

