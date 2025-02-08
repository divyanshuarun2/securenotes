package com.secure.notes.services;

import com.secure.notes.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User updateUserRole(Long userId, String rolename);

    User getUser(Long id);
}
