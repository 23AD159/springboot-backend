package com.example.springintern.services;

import com.example.springintern.jwt.JwtTokenProvider;
import com.example.springintern.models.JwtResponse;
import com.example.springintern.models.RegisterDetails;
import com.example.springintern.models.Roles;
import com.example.springintern.models.UserDetailsDto;
import com.example.springintern.repository.RegisterDetailsRepository;
import com.example.springintern.repository.RegisterRepository;
import com.example.springintern.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleNames()) {
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("User not found " + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        System.out.println("Registration" + registerDetails);
        registerDetailsRepository.save(registerDetails);

        return "Employee added Successfully";
    }

    public String authenticate(RegisterDetails login){
        Authentication authentication =
                authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(),
                        login.getPassword()));
        return jwtTokenProvider.generateToken(authentication);
    }

    public Optional<RegisterDetails> getUserByUserName(String username){
        return registerRepository.findByUserName(username);
    }

    public List<RegisterDetails> getAllEmployees() {
        return registerDetailsRepository.findAll();
    }
}
