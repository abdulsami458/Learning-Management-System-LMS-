package com.astra.learning.management.system.service;

import com.astra.learning.management.system.model.User;
import com.astra.learning.management.system.principal.UserPrinipal;
import com.astra.learning.management.system.repository.UserRepo;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Email Not Found"));
        return new UserPrinipal(user);
    }
}
