package com.ferriswheel.datasource.multi;

import com.ferriswheel.datasource.multi.mapper.db1.UserMapper;
import com.ferriswheel.datasource.multi.model.db1.User;
import com.ferriswheel.datasource.multi.model.db2.Class;
import com.ferriswheel.datasource.multi.mapper.db2.ClassMapper;
import com.ferriswheel.datasource.multi.mapper.db3.CourseMapper;
import com.ferriswheel.datasource.multi.model.db3.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringBootDatasourceMultiApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private CourseMapper courseMapper;


    @Test
    void contextLoads() {
    }

    @Test
    void getUserList() {
        List<User> userList = this.userMapper.findAll();

        assertTrue(userList.size() > 0);
    }

    @Test
    void getClassList() {
        List<Class> classList = this.classMapper.findAll();

        assertTrue(classList.size() > 0);
    }

    @Test
    void getCfgCourseList() {
        List<Course> courseList = this.courseMapper.findAll();

        assertTrue(courseList.size() > 0);
    }

}
