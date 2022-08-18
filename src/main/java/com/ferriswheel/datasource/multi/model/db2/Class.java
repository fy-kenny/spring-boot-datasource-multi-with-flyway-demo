package com.ferriswheel.datasource.multi.model.db2;

import lombok.Data;

import java.util.Date;

@Data
public class Class {
    private Integer id;
    private String name;
    private Integer type;
    private Date createTime;
    private Date updateTime;
}
