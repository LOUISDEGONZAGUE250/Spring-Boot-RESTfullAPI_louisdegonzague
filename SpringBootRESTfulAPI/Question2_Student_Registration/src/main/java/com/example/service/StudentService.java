package com.example.service;

import com.example.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private Long nextId = 1L;

    public StudentService() {
        // Initialize with sample data
        initializeSampleData();
    }

    private void initializeSampleData() {
        students.put(1L, new Student(1L, "John", "Smith", "john.smith@email.com", "Computer Science", 3.8));
        students.put(2L, new Student(2L, "Sarah", "Johnson", "sarah.johnson@email.com", "Computer Science", 3.9));
        students.put(3L, new Student(3L, "Michael", "Brown", "michael.brown@email.com", "Mathematics", 3.6));
        students.put(4L, new Student(4L, "Emily", "Davis", "emily.davis@email.com", "Computer Science", 3.5));
        students.put(5L, new Student(5L, "David", "Wilson", "david.wilson@email.com", "Physics", 3.2));
        nextId = 6L;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    // Get student by ID
    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    // Get students by major
    public List<Student> getStudentsByMajor(String major) {
        return students.values().stream()
                .filter(student -> student.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }

    // Filter students by minimum GPA
    public List<Student> filterStudentsByGpa(Double minGpa) {
        return students.values().stream()
                .filter(student -> student.getGpa() >= minGpa)
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .collect(Collectors.toList());
    }

    // Register a new student
    public Student registerStudent(Student student) {
        student.setStudentId(nextId);
        students.put(nextId, student);
        nextId++;
        return student;
    }

    // Update student information
    public Student updateStudent(Long studentId, Student studentDetails) {
        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            if (studentDetails.getFirstName() != null) {
                student.setFirstName(studentDetails.getFirstName());
            }
            if (studentDetails.getLastName() != null) {
                student.setLastName(studentDetails.getLastName());
            }
            if (studentDetails.getEmail() != null) {
                student.setEmail(studentDetails.getEmail());
            }
            if (studentDetails.getMajor() != null) {
                student.setMajor(studentDetails.getMajor());
            }
            if (studentDetails.getGpa() != null) {
                student.setGpa(studentDetails.getGpa());
            }
            return student;
        }
        return null;
    }
}
