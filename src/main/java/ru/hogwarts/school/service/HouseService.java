package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class HouseService {

    @Autowired
    private FacultyRepository facultyRepository;
    public Faculty createFaculty(Faculty faculty){return facultyRepository.save(faculty);}
    public Faculty findFaculty(long id){return facultyRepository.findById(id).get();}
    public Faculty editFaculty(Faculty faculty){return facultyRepository.save(faculty);}
    public void deleteFaculty(Long id){facultyRepository.deleteById(id);}
    public Collection<Faculty> getFaculties(){return facultyRepository.findAll();}
    public Collection<Faculty> getFacultyByColor(String color){return facultyRepository.findByColor(color);}
}
