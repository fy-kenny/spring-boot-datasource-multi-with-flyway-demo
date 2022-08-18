package com.ferriswheel.datasource.multi.service;

import com.ferriswheel.datasource.multi.model.db1.User;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
public interface UserService {
    List<User> findAll();
}
