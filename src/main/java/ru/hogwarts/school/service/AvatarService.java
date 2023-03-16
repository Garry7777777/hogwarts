package ru.hogwarts.school.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.*;
import ru.hogwarts.school.repository.AvatarRepository;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
@Transactional
public class AvatarService {

    @Value(value = "${application.avatars.folder}")
    private String avatarsDir;

    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private StudentService studentService;

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        log.info(" Вызов метода uploadAvatar");
        Student student = studentService.findStudent(studentId).toStudent();

        Path filePath = Path.of(avatarsDir, studentId + "." +  getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        log.error(" Отсутствует файл аватара " );
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
            Avatar avatar = findAvatar(studentId);
            avatar.setStudent(student);
            avatar.setFilePath(filePath.toString());
            avatar.setFileSize(file.getSize());
            avatar.setMediaType(file.getContentType());
            avatar.setPreview(generateImagePreview(filePath));

            avatarRepository.save(avatar);

    }

    private byte[] generateImagePreview(Path filePath) throws IOException{
        log.info(" Вызов метода generateImagePreview");
        log.error(" Отсутствует файл аватара " );
        try (   InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }
    public Avatar findAvatar(Long studentId) {
        log.info(" Вызов метода findAvatar");
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtension(String fileName) {
        log.info(" Вызов метода getExtension");
        return fileName.substring(fileName.lastIndexOf(  ".") + 1);
    }
}