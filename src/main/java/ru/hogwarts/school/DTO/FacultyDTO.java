package ru.hogwarts.school.DTO;


import lombok.Data;
import org.springframework.beans.BeanUtils;
import ru.hogwarts.school.model.Faculty;


@Data
public class FacultyDTO {

    private long id;
    private String name;
    private String color;

    public static FacultyDTO fromFaculty(Faculty faculty){
        var dto = new FacultyDTO();
        BeanUtils.copyProperties(faculty, dto);
        return dto;
    }
    public Faculty toFaculty(){
        Faculty faculty = new Faculty();
        BeanUtils.copyProperties(this, faculty);
        return faculty;
    }



}
