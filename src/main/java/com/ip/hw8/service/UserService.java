package com.ip.hw8.service;

import com.ip.hw8.entity.Role;
import com.ip.hw8.entity.User;
import com.ip.hw8.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailFetchRole(username);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        userRepository.save(user);
    }

    public void updateUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
