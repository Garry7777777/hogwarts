package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.AppInfo;

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
    public AppInfo getAppInfo(){
        return new AppInfo(name, version, environment);
    }
}