package com.example.springintern.controllers;

import com.example.springintern.models.RegisterDetails;
import com.example.springintern.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;


    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoute(){
        String result = employeeController.router();
        System.out.println(result);
        assertEquals("Welcome to Spring Boot security!",result);
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(employeeService.getMethod()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result = employeeController.getMethod();
        assertEquals(2,result.size());
    }

    @Test
    void testPostMethod() {
        RegisterDetails emp = new RegisterDetails();
        when(employeeService.addEmployee(emp)).thenReturn("Employee added");
        String result = employeeController.postMethod(emp);
        assertEquals("Employee added", result);
    }

    @Test
    void testPutMethod() {
        when(employeeService.updateEmployee(102)).thenReturn("Employee updated");
        String result = employeeController.putMethod(102);
        assertEquals("Employee updated", result);
    }

    @Test
    void testDeleteMethod() {
        when(employeeService.deleteEmployeeById(103)).thenReturn("Employee deleted");
        String result = employeeController.deleteMethod(103);
        assertEquals("Employee deleted", result);
    }

    @Test
    void testGetLoggedInUserDetailsFromService() {
        when(employeeService.getCurrentUserWithRoles()).thenReturn("Logged in user: Yamuna, Role: ADMIN");
        String result = employeeController.getLoggedInUserDetailsFromService();
        assertEquals("Logged in user: Yamuna, Role: ADMIN", result);
    }
}
