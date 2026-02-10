package com.example.service;

import com.example.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    // Get all students
    public List<Student> getAllStudents() {
        return students;
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Get students by major
    public List<Student> getStudentsByMajor(String major) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }
        return result;
    }

    // Filter students by GPA
    public List<Student> filterStudentsByGpa(Double gpa) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getGpa() >= gpa) {
                result.add(student);
            }
        }
        return result;
    }

    // Add new student
    public Student registerStudent(Student student) {
        student.setStudentId(nextId++);
        students.add(student);
        return student;
    }

    // Update student
    public Student updateStudent(Long id, Student studentDetails) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                student.setFirstName(studentDetails.getFirstName());
                student.setLastName(studentDetails.getLastName());
                student.setEmail(studentDetails.getEmail());
                student.setMajor(studentDetails.getMajor());
                student.setGpa(studentDetails.getGpa());
                return student;
            }
        }
        return null;
    }

    // Delete student
    public boolean deleteStudent(Long id) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }
}


