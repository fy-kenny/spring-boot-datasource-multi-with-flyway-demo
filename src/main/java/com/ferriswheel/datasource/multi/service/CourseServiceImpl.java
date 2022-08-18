package com.ferriswheel.datasource.multi.service;

import com.ferriswheel.datasource.multi.mapper.db3.CourseMapper;
import com.ferriswheel.datasource.multi.model.db3.Course;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@Service
public class CourseServiceImpl implements CourseService {

    private CourseMapper courseMapper;

    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public List<Course> findAll() {
        return this.courseMapper.findAll();
    }
}
