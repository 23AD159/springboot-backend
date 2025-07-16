package com.example.springintern.services;

import com.example.springintern.models.RegisterDetails;
import com.example.springintern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    RegisterDetailsRepository registerDetailsRepository;
    
    public List<RegisterDetails> getMethod() {
        return registerDetailsRepository.findAll();
    }

    public RegisterDetails getEmployeeById(int empId) {
        return registerDetailsRepository.findById(empId).orElse(new RegisterDetails());
    }

//    public List<RegisterDetails> getEmployeeByJob(String job) {
//        return registerDetailsRepository.findAll();
//
//    }

    public String addEmployee(RegisterDetails emp) {
        registerDetailsRepository.save(emp);
        return "Employee added successfully";
    }

    public String updateEmployee(int empId) {
        RegisterDetails user = registerDetailsRepository.findById(empId)
                .orElseThrow(()->new RuntimeException("Employee not found"));

        registerDetailsRepository.save(user);
        return "Employee updated successfully";
    }

    public String deleteEmployeeById(int empId) {
        registerDetailsRepository.deleteById(empId);
        return "Employee deleted successfully";
    }

//    public String getCurrentUserWithRoles() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String username = authentication.getName();
//        Set<String> roles = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toSet());
//
//        return "Username: " + username + ", Roles: " + roles;
//    }
    public String getCurrentUserWithRoles() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    String username = authentication.getName(); // get the username

    String roles = "";
    for (GrantedAuthority authority : authentication.getAuthorities()) {
        roles += authority.getAuthority() + " "; // append each role
    }

    return "Username: " + username + ", Roles: " + roles.trim();
}

}
