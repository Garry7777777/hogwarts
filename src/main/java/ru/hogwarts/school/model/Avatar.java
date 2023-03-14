package ru.hogwarts.school.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    private Student student;

    public void setPreview(byte[] preview){
        setData(preview);
    }
    public byte[] getPreview(){
        return getData();
    }

}
