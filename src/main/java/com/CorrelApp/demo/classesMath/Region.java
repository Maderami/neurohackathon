package com.CorrelApp.demo.classesMath;
import lombok.Getter;
import lombok.Setter;

public class Region {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private long upid;
    @Getter
    @Setter
    private String name;

    public Region(long id, long upid, String name) {
        this.id = id;
        this.upid = upid;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUpid() {
        return upid;
    }

    public void setUpid(long upid) {
        this.upid = upid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region() {
    }
}