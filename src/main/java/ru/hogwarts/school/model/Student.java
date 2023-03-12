package ru.hogwarts.school.model;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Student {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;
}
