package com.ferriswheel.datasource.multi.controller;

import com.ferriswheel.datasource.multi.model.db2.Class;
import com.ferriswheel.datasource.multi.service.ClassService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@RestController
public class ClassController {

    private ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/api/v1/classes")
    public List<Class> findAll() {
        return this.classService.findAll();
    }
}
