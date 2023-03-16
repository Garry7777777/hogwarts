package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.AppInfo;

@RestController
@RequestMapping("/appinfo")
public class InfoController {

    @GetMapping
    public AppInfo getAppInfo(){
        return new AppInfo();
    }
}