package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import java.util.*;


@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student createStudent(Student student){return studentRepository.save(student);}
    public Student findStudent(long id){return studentRepository.findById(id).get();}
    public Student editStudent(Student student){return studentRepository.save(student);}
    public void deleteStudent(Long id) {studentRepository.deleteById(id);}
    public Collection<Student> getStudents(){return studentRepository.findAll();}
    public Collection<Student> getStudentsByAge(int age) {return studentRepository.findByAge(age);}
}
