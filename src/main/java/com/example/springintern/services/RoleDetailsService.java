package com.example.springintern.services;


import com.example.springintern.models.RegisterDetails;
import com.example.springintern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleDetailsService {
    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<RegisterDetails> getUsersByRole(String roleName) {
        return  registerDetailsRepository.findByRoleName(roleName);
    }

    public String updateUserById(int id, RegisterDetails updatedDetails) {
        RegisterDetails existing = registerDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setName(updatedDetails.getName());
        existing.setUserName(updatedDetails.getUserName());
        existing.setEmail(updatedDetails.getEmail());
        existing.setPassword(passwordEncoder.encode(updatedDetails.getPassword()));
        existing.setRoles(updatedDetails.getRoles());

        registerDetailsRepository.save(existing);
        return "Employee updated successfully";

    }
}