package com.ferriswheel.datasource.multi.service;

import com.ferriswheel.datasource.multi.model.db3.Course;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
public interface CourseService {
    List<Course> findAll();
}
