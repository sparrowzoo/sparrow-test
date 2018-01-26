package com.sparrow.redis;

import com.sparrow.support.Entity;

/**
 * Created by harry on 2018/1/26.
 */
public class RedisEntity implements Entity {

    public RedisEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
