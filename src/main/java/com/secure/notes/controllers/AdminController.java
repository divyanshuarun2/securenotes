package com.secure.notes.controllers;

import com.secure.notes.entity.User;
import com.secure.notes.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    UserService userService;

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList= userService.getAllUsers();
        if(userList!=null){
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/upate-role")
    public ResponseEntity<User> updateUserRole( @RequestParam Long userId,
                                               @RequestParam String rolename){
        User user = userService.updateUserRole(userId, rolename);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user= userService.getUser(id);
        if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
