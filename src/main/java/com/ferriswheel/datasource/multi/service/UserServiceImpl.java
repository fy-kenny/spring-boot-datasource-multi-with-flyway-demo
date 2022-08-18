package com.ferriswheel.datasource.multi.service;

import com.ferriswheel.datasource.multi.mapper.db1.UserMapper;
import com.ferriswheel.datasource.multi.model.db1.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
        return this.userMapper.findAll();
    }
}
