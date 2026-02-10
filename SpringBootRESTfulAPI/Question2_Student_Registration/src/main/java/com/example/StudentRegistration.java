package com.example.controller;

import com.example.model.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class StudentRegistrationController {

    @Autowired
    private StudentService studentService;

    // Register a new student
    @PostMapping
    public Student registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }
}


