package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import java.util.Collection;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private  StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    @PutMapping
    public ResponseEntity<StudentDTO> editStudent(@RequestBody StudentDTO studentDTO ) {
        StudentDTO editStudent = studentService.editStudent(studentDTO);
        if (editStudent == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        if (studentService.findStudent(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentService.findStudent(id));
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> getStudentsByAge(@RequestParam(required = false) Integer age,
                                                                @RequestParam(required = false) Integer min,
                                                                @RequestParam(required = false) Integer max) {
        if (age == null & min == null & max == null)
                return ResponseEntity.ok(studentService.getStudents());  // нет параметров
        if (age != null & min == null & max == null)
                return ResponseEntity.ok(studentService.getStudentsByAge(age)); // один параметр
        if (age == null & min!=null & max!=null)
                return ResponseEntity.ok(studentService.getStudentsByAges(min,max)); // два параметра
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<FacultyDTO> getFacultyByStudentId(@PathVariable Long id) {
        FacultyDTO facultyDTO = studentService.getFacultyByStudentId(id);
        return ResponseEntity.ok(facultyDTO);
    }

}