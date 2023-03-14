package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.DTO.*;
import ru.hogwarts.school.model.*;
import ru.hogwarts.school.repository.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FacultyRepository facultyRepository;

    public StudentDTO createStudent(StudentDTO studentDTO){
        Student student = studentDTO.toStudent();
        student.setFaculty(facultyRepository.findById(studentDTO.getFacultyId()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }
    public StudentDTO editStudent(StudentDTO studentDTO){
        return StudentDTO.fromStudent(studentRepository.save(studentDTO.toStudent()));
    }
    public StudentDTO findStudent(long id){
        return StudentDTO.fromStudent(studentRepository.findById(id).get());
    }
    public void deleteStudent(Long id) { studentRepository.deleteById(id);}



    public Collection<StudentDTO> getStudents(){
        return studentRepository.findAll()
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }
    public Collection<StudentDTO> getStudentsByAge(int age){
        return studentRepository.findByAge(age)
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }
    public Collection<StudentDTO> getStudentsByAges(int min, int max){
        return studentRepository.findByAgeBetween(min, max)
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

    public FacultyDTO getFacultyByStudentId(Long id) {
        return FacultyDTO.fromFaculty(facultyRepository.findById(
                StudentDTO.fromStudent(studentRepository.findById(id).get()).getFacultyId()).get());
    }

    public Long countAllStudents(){
        return studentRepository.countAll();
    }
    public Long countStudentsMidlife(){
        return studentRepository.countMidlife();
    }
    public Collection<StudentDTO> findYoungestStudents(){
        return studentRepository.findYoungest()
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());}

}
