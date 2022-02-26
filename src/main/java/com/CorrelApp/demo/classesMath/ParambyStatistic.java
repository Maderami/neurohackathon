package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import lombok.Setter;

public class ParambyStatistic {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String name;

    public ParambyStatistic(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParambyStatistic() {
    }
}