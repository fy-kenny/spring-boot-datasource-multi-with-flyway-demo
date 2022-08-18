package com.ferriswheel.datasource.multi.model.db1;

import lombok.Data;

import java.util.Date;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String nickName;
    private Date createTime;
    private Date updateTime;
}
