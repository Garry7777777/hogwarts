package ru.hogwarts.school.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Faculty {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String color;
}
