package com.ferriswheel.datasource.multi.mapper.db3;


import com.ferriswheel.datasource.multi.model.db3.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> findAll();
}
