package com.ferriswheel.datasource.multi.service;

import com.ferriswheel.datasource.multi.model.db2.Class;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
public interface ClassService {
    List<Class> findAll();
}
