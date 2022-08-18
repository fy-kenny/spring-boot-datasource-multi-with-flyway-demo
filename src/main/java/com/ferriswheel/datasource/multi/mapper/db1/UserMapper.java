package com.ferriswheel.datasource.multi.mapper.db1;

import com.ferriswheel.datasource.multi.model.db1.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@Mapper
public interface UserMapper {

    List<User> findAll();
}
