package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;
import java.util.Collection;


@RestController
@RequestMapping("/faculty")
public class HouseController {


    @Autowired
    private  HouseService houseService;

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(houseService.createFaculty(faculty));
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = houseService.editFaculty(faculty);
        if (editFaculty == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        houseService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(houseService.findFaculty(id));
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFacultiesByColor(@RequestParam(required = false) String color) {
        if (color == null) return ResponseEntity.ok(houseService.getFaculties());
        return ResponseEntity.ok(houseService.getFacultyByColor(color));
    }

}