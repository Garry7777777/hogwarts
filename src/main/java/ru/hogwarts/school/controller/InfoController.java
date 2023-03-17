package ru.hogwarts.school.controller;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appinfo")
public class InfoController {

    @Data @AllArgsConstructor
    static class AppInfo {String appName, appVersion, appEnvironment;}

    @Value("${app.info.name: hogwarts-school}")
    private String name ;
    @Value("${app.info.version: 0.0.1}")
    private String version;
    @Value("${app.info.environment}")
    private String environment;

    @GetMapping
    public AppInfo getAppInfo(){
        return  new AppInfo(name, version, environment);
    }
}