package com.CorrelApp.demo.classesMath;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class BigRegion {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String name;
    @Getter
    private List<Region> regionList;

    public BigRegion(long id, String name) {
        this.id = id;
        this.name = name;
        regionList = new ArrayList<>();
    }

    public void Add(Region region) { regionList.add(region); }

    public Region findById(long id) {
        for (Region region : regionList)
            if (region.getId()== id)
                return region;
        return null;
    }

    public BigRegion() {
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

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }
}