package ru.hogwarts.school.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.DTO.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    public StudentDTO createStudent(StudentDTO studentDTO){
        log.info(" Вызов метода createStudent");
        Student student = studentDTO.toStudent();
        student.setFaculty(facultyRepository.findById(studentDTO.getFacultyId()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }

    public StudentDTO editStudent(StudentDTO studentDTO){
        log.info(" Вызов метода editStudent");
        return StudentDTO.fromStudent(studentRepository.save(studentDTO.toStudent()));
    }

    public StudentDTO findStudent(long id) {
        log.info(" Вызов метода findStudent");
        return StudentDTO.fromStudent(studentRepository.findById(id).get());
    }
    public void deleteStudent(Long id) {
        log.info(" Вызов метода deleteStudent");
        studentRepository.deleteById(id);}


    public Collection<StudentDTO> getStudents(){
        log.info(" Вызов метода getStudents");
        return studentRepository.findAll()
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

    public Collection<StudentDTO> getStudentsByAge(int age){
        log.info(" Вызов метода getStudentsByAge");
        return studentRepository.findByAge(age)
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

    public Collection<StudentDTO> getStudentsByAges(int min, int max){
        log.info(" Вызов метода getStudentsByAges");
        return studentRepository.findByAgeBetween(min, max)
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }


    public FacultyDTO getFacultyByStudentId(Long id) {
        log.info(" Вызов метода getFacultyByStudentId");
        return FacultyDTO.fromFaculty(facultyRepository.findById(
                StudentDTO.fromStudent(studentRepository.findById(id).get()).getFacultyId()).get());
    }

    public Long countAllStudents(){
        log.info(" Вызов метода countAllStudents");
        return studentRepository.countAll();
    }

    public Long countStudentsMidlife(){
        log.info(" Вызов метода countStudentsMidlife");
        return studentRepository.countMidlife();
    }

    public Collection<StudentDTO> findYoungestStudents(){
        log.info(" Вызов метода findYoungestStudents");
        return studentRepository.findYoungest()
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

    public Collection<StudentDTO> getStudentsPages(Pageable pageable)  {
        log.info(" Вызов метода getStudentsPages");
        return studentRepository.findAll(pageable)
                .getContent().stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }


}
