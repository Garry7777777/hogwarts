package ru.hogwarts.school.controller;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appinfo")
public class InfoController {

    @Value("${app.info.name: hogwarts-school}")
    private String name ;
    @Value("${app.info.version: 0.0.1}")
    private String version;
    @Value("${app.info.environment}")
    private String environment;

    @GetMapping
    public Object getAppInfo(){

        @Data
        @AllArgsConstructor
        class AppInfo {
            private String appName ;
            private String appVersion;
            private String appEnvironment;
        }

        return new AppInfo(name, version, environment);
    }
}