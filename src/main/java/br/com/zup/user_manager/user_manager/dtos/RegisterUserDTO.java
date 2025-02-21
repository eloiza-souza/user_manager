package br.com.zup.user_manager.user_manager.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDTO {
    private String name;
    private String userName;
    private String password;
    private Set<Roles> roles;

}
