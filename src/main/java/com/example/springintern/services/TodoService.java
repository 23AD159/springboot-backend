package com.example.springintern.services;

import com.example.springintern.models.RegisterDetails;
import com.example.springintern.models.Todo;
import com.example.springintern.repository.RegisterDetailsRepository;
import com.example.springintern.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;

    public String assignTodoById(int empId, Todo todo) {
        RegisterDetails user = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        todo.setAssignedEmployee(user);
        todoRepository.save(todo);

        return "Todo assigned to employee ID: " + empId;
    }
}
