package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/faculty")
public class HouseController {


    @Autowired
    private  HouseService houseService;

    @PostMapping
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDTO) {
        return ResponseEntity.ok(houseService.createFaculty(facultyDTO));
    }

    @PutMapping
    public ResponseEntity<FacultyDTO> editFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO editFaculty = houseService.editFaculty(facultyDTO);
        if (editFaculty == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        houseService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<FacultyDTO> getFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(houseService.findFaculty(id));
    }
    @GetMapping
    public ResponseEntity<Collection<FacultyDTO>> getFacultiesByColor(@RequestParam(required = false) String color,
                                                                   @RequestParam(required = false) String name) {
        if (color != null & name != null ) return ResponseEntity.badRequest().build();
        if (color != null) return ResponseEntity.ok(houseService.getFacultyByColor(color));
        if (name != null) return ResponseEntity.ok(houseService.getFacultyByName(name));
        return ResponseEntity.ok(houseService.getFaculties());
    }
    @GetMapping("{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByFacultyId(@PathVariable Long id){
        return ResponseEntity.ok(houseService.getStudentsByFacultyId(id));
    }

}