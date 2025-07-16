package com.example.springintern.controllers;

import com.example.springintern.models.RegisterDetails;
import com.example.springintern.models.UserDetailsDto;
import com.example.springintern.services.AuthService;
import com.example.springintern.services.RoleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class RoleDetailsController {

    @Autowired
    RoleDetailsService  roleDetailsService;

    @GetMapping("/role/{roleName}")
    public List<RegisterDetails> getUsersByRole(@PathVariable String roleName) {
        return roleDetailsService.getUsersByRole(roleName);
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody RegisterDetails updatedDetails) {
        return roleDetailsService.updateUserById(id, updatedDetails);
    }
}
