package com.ferriswheel.datasource.multi.mapper.db2;


import com.ferriswheel.datasource.multi.model.db2.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<Class> findAll();
}
