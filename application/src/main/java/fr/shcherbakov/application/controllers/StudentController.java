package fr.shcherbakov.application.controllers;

import fr.shcherbakov.application.models.Student;
import fr.shcherbakov.application.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }


    @PostMapping("/students")
    public void registerStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "/students/{studentID}")
    public void deleteStudent(@PathVariable("studentID") Long studentID) {
        studentService.deleteStudent(studentID);
    }

    @PutMapping(path = "/students/{studentID}")
    public void updateStudent (@PathVariable("studentID") Long studentID,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email) {
        studentService.updateStudent(studentID, name, email);

    }
}
