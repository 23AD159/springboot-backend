package com.example.springintern.controllers;

import com.example.springintern.models.Todo;
import com.example.springintern.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/assign/{empId}")
    public String assignTodoById(@PathVariable int empId, @RequestBody Todo todo) {
        return todoService.assignTodoById(empId, todo);
    }
}
