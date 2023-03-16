package ru.hogwarts.school.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Data
public class AppInfo {
    @Value("${app.name: hogwarts-school}")
    private String appName;
    @Value("${app.version: 0.0.1}")
    private String appVersion;
    @Value("${app.env}")
    private String appEnvironment;
}
