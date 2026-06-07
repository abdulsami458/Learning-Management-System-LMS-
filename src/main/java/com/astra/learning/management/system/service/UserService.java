package com.astra.learning.management.system.service;

import com.astra.learning.management.system.Role.Role;
import com.astra.learning.management.system.dto.UserDto;
import com.astra.learning.management.system.model.User;
import com.astra.learning.management.system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Value("${admin.password}")
    private String password;
    @Value("${admin.email}")
    private String email;
    @Value("${admin.name}")
    private String name;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserDto userDto , Role role){
        if(userRepo.findByEmail(userDto.getEmail()).isPresent()){
            throw new RuntimeException("Email Already Registered");
        }
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(role);
        return userRepo.save(user);
    }



    public List<User> getUsers(){
        return userRepo.findAll();
    }


    @Bean
    CommandLineRunner init(UserRepo userRepo){
        return args -> {
            if(userRepo.findByEmail(email).isEmpty()){
                User admin = new User();
                admin.setRole(Role.ROLE_ADMIN);
                admin.setUserName(name);
                admin.setEmail(email);
                admin.setPassword(passwordEncoder.encode(password));
                userRepo.save(admin);
            }
        };
    }
}
