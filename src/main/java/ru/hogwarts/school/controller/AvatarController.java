package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.*;

@RestController
@RequestMapping("/student")
public class AvatarController {

        @Autowired
        private AvatarService avatarService;

        @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadCover(@PathVariable Long id,
                                                  @RequestParam MultipartFile avatar) throws IOException {
            if (avatar.getSize() >= 1024 * 300) return ResponseEntity.badRequest().body("File is too big");
            avatarService.uploadAvatar(id,avatar);
            return ResponseEntity.ok().build();
        }

        @GetMapping("/{Id}/preview")
        public ResponseEntity<byte[]> downloadAvatar( @PathVariable Long Id){
            Avatar avatar = avatarService.findAvatar(Id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
            headers.setContentLength(avatar.getPreview().length);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body( avatar.getPreview());
        }

        @GetMapping("/{id}/avatar")
        public void downloadCover(@PathVariable Long id, HttpServletResponse response) throws IOException {
            Avatar avatar = avatarService.findAvatar(id);
            Path path = Path.of(avatar.getFilePath());
            try (InputStream is = Files.newInputStream(path);
                 OutputStream os = response.getOutputStream()
            ) {
                response.setContentType(avatar.getMediaType());
                response.setContentLength((int) avatar.getFileSize());

                is.transferTo(os);
            }
        }
}