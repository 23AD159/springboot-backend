package com.example.springintern.controllers;

import com.example.springintern.models.RegisterDetails;
import com.example.springintern.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService hws;

    @PreAuthorize("hasAnyRole('ADMIN','USER','ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/")
    public String router(){
        return "Welcome to Spring Boot security!";
    }

    @GetMapping("/employee")
    @PreAuthorize("hasAnyRole('ADMIN','USER','ROLE_ADMIN','ROLE_USER')")
    public List<RegisterDetails> getMethod(){
        return hws.getMethod();
    }

    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','ROLE_ADMIN','ROLE_USER')")
    public RegisterDetails getMethodById(@PathVariable int empId){
        System.out.println();
        return hws.getEmployeeById(empId);
    }

//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @GetMapping("/job/{job}")
//    public List<RegisterDetails> getEmployeeByJob(@PathVariable String job){
//        return hws.getEmployeeByJob(job);
//    }

//    @GetMapping("/firstName/{firstName}")
//    public List<Employee> getEmployeeByFirstName(@PathVariable String firstName){
//        return hws.getEmployeeByFirstName(firstName);
//    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/employee")
    public String postMethod(@RequestBody RegisterDetails employee){
        return hws.addEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employee/{empId}")
    public String putMethod(@PathVariable int empId){
        return hws.updateEmployee(empId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employee/{empId}")
    public String deleteMethod(@PathVariable int empId){
        return hws.deleteEmployeeById(empId);
    }
//    @DeleteMapping("/")
//    public String deleteAllMethod(){
//        return hws.deleteAllEmployees();
//    }

    @GetMapping("/userinfo")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String getLoggedInUserDetailsFromService() {
        return hws.getCurrentUserWithRoles();
    }

}