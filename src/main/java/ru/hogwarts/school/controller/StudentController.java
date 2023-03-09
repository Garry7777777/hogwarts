package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import java.util.Collection;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private  StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        if (studentService.findStudent(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentService.findStudent(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudentsByAge(@RequestParam(required = false) Integer age) {
        if (age == null) return ResponseEntity.ok(studentService.getStudents());
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

}