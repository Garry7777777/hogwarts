package ru.hogwarts.school.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.DTO.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HouseService {

    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyDTO createFaculty(FacultyDTO facultyDTO){
        log.info(" Вызов метода createFaculty" );
        Faculty faculty = facultyDTO.toFaculty();
        faculty.setStudents( new LinkedList<>());
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));
    }

    public FacultyDTO editFaculty(FacultyDTO facultyDTO){
        log.info(" Вызов метода editFaculty");
        return FacultyDTO.fromFaculty(facultyRepository.save(facultyDTO.toFaculty()));
    }

    public FacultyDTO findFaculty(long id){
        log.info(" Вызов метода findFaculty");
        return FacultyDTO.fromFaculty(facultyRepository.findById(id).get());
    }

    public void deleteFaculty(Long id){
        log.info(" Вызов метода deleteFaculty");
        facultyRepository.deleteById(id);
    }



    public Collection<FacultyDTO> getFaculties(){
        log.info(" Вызов метода getFaculties");
        return facultyRepository.findAll().stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public Collection<FacultyDTO> getFacultyByColor(String color){
        log.info(" Вызов метода getFacultyByColor");
        return facultyRepository.findByColor(color).stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public Collection<FacultyDTO> getFacultyByName(String name) {
        log.info(" Вызов метода getFacultyByName");
        return facultyRepository.findByNameIgnoreCase(name).stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }


    public List<StudentDTO> getStudentsByFacultyId(Long id) {
        log.info(" Вызов метода getStudentsByFacultyId");
        return facultyRepository.findById(id).get().getStudents()
                .stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }
}
