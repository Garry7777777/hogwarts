package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.DTO.*;
import ru.hogwarts.school.model.*;
import ru.hogwarts.school.repository.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HouseService {

    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyDTO createFaculty(FacultyDTO facultyDTO){
        Faculty faculty = facultyDTO.toFaculty();
        faculty.setStudents( new LinkedList<>());
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));
    }
    public FacultyDTO editFaculty(FacultyDTO facultyDTO){
        return FacultyDTO.fromFaculty(facultyRepository.save(facultyDTO.toFaculty()));
    }

    public FacultyDTO findFaculty(long id){
        return FacultyDTO.fromFaculty(facultyRepository.findById(id).get());
    }
    public void deleteFaculty(Long id){
        facultyRepository.deleteById(id);
    }



    public Collection<FacultyDTO> getFaculties(){
        return facultyRepository.findAll().stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }
    public Collection<FacultyDTO> getFacultyByColor(String color){
        return facultyRepository.findByColor(color).stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }
    public Collection<FacultyDTO> getFacultyByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name).stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }


    public List<StudentDTO> getStudentsByFacultyId(Long id) {
        return facultyRepository.findById(id).get().getStudents()
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }
}
