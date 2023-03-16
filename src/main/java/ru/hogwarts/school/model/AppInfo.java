package ru.hogwarts.school.model;

import lombok.*;

@Data
@AllArgsConstructor
public class AppInfo {
    private String appName ;
    private String appVersion;
    private String appEnvironment;
}
