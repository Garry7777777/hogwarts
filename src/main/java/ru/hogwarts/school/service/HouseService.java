package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import java.util.*;

@Service
public class HouseService {

    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long lastId = 0L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        if (faculties.containsKey(id))
            return faculties.get(id);
        return null;
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId()))
            return faculties.put(faculty.getId(), faculty);
        return null;
    }

    public Faculty deleteFaculty(Long id) {
        if (faculties.containsKey(id))
            return faculties.remove(id);
        return null;
    }

    public Collection<Faculty> getFaculties() {
        return faculties.values();
    }


}
