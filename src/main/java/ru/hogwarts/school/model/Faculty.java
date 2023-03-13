package ru.hogwarts.school.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Faculty {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String color;
    @OneToMany(mappedBy = "faculty")
    private List<Student> students;
}
