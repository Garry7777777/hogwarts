package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FacultyRepository facultyRepository;

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

    public Collection<StudentDTO> getStudentsPages(Pageable pageable)  {
        return studentRepository.findAll(pageable)
                .getContent().stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }


}
