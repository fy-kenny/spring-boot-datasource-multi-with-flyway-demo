package com.ferriswheel.datasource.multi.controller;

import com.ferriswheel.datasource.multi.model.db3.Course;
import com.ferriswheel.datasource.multi.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/api/v1/courses")
    public List<Course> findAll() {
        return this.courseService.findAll();
    }
}
