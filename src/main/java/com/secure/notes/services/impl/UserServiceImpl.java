package com.secure.notes.services.impl;

import com.secure.notes.entity.AppRole;
import com.secure.notes.entity.Role;
import com.secure.notes.entity.User;
import com.secure.notes.repositories.RoleRepo;
import com.secure.notes.repositories.UserRepository;
import com.secure.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUserRole(Long userId, String rolename) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("Username with "+userId+" not found"));
        AppRole appRole = AppRole.valueOf(rolename);
        Role role = roleRepo.findByRoleName(appRole).orElseThrow(() ->
                new RuntimeException("Role not found"));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
}
