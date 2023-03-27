package ru.hogwarts.school.model;

import lombok.Data;
import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Data
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
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
