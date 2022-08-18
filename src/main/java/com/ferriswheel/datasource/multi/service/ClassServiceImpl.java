package com.ferriswheel.datasource.multi.service;

import com.ferriswheel.datasource.multi.mapper.db2.ClassMapper;
import com.ferriswheel.datasource.multi.model.db2.Class;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@Service
public class ClassServiceImpl implements ClassService {

    private ClassMapper classMapper;

    public ClassServiceImpl(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    @Override
    public List<Class> findAll() {
        return this.classMapper.findAll();
    }
}
