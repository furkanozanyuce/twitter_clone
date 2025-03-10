package com.foy.twitter.service;

import com.foy.twitter.entity.Role;
import com.foy.twitter.entity.User;
import com.foy.twitter.exceptions.TwitterException;
import com.foy.twitter.repository.RoleRepository;
import com.foy.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{

    private  UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            throw new TwitterException("Email already registered!", HttpStatus.CONFLICT);
        }

        String encodedPassword = passwordEncoder.encode(password);

        Optional<Role> userRole = roleRepository.findByAuthority("USER");

        if (userRole.isEmpty()) {
            Role role = new Role();
            role.setAuthority("USER");

            userRole = Optional.of(roleRepository.save(role));
        }
        //admin?, user?

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(Set.of(userRole.get()));

        return userRepository.save(user);
    }
}
