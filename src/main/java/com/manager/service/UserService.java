package com.manager.service;

import com.manager.configuration.UserDetailsAdapter;
import com.manager.model.*;
import com.manager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Users addUser(Users user) {
        return userRepository.save(user);
    }

    public Users getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(s);
        return new UserDetailsAdapter(user);
    }
}
