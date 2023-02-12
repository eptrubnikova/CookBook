package me.trubnikova.cookbook.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String getLaunch() {
        return "Приложение запущено";
    }

    @GetMapping("/path/to/info")
    public String getInfo(@RequestParam String info) {
        info = "имя ученика: Елена,\n" +
                "название проекта: Кулинарная книга,\n" +
                "дата создания проекта: 30.01.2023,\n" +
                "описание проекта: учебное web-приложение";
        return info;
    }
}
