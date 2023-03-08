package ru.hogwarts.school.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import java.util.*;

@Service
public class StudentService {

    private Map<Long,Student> students = new HashMap<>() ;
    private Long lastId = 0L;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(Long id) {
        if (students.containsKey(id))  return students.get(id);
        return null;
    }

    public Student editStudent(Student students) {
        if (students.containsKey(students.getId())) return students.put(students.getId(), students);
        return null;
    }

    public Student deleteStudent(Long id) {
        if (students.containsKey(id)) return students.remove(id);
        return null;
    }

    public Collection<Student> getStudents() {
        return students.values();
    }

}
