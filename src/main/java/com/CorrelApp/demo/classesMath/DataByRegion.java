package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import lombok.Setter;

public class DataByRegion {
    @Getter
    @Setter
    private long paramId;
    @Getter
    @Setter
    private long regionId;
    @Getter
    @Setter
    private long yearId;
    @Getter
    @Setter
    private double value;

    public DataByRegion(long paramId, long regionId, long yearId, double value) {
        this.paramId = paramId;
        this.regionId = regionId;
        this.yearId = yearId;
        this.value = value;
    }

    public long getParamId() {
        return paramId;
    }

    public void setParamId(long paramId) {
        this.paramId = paramId;
    }

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    public long getYearId() {
        return yearId;
    }

    public void setYearId(long yearId) {
        this.yearId = yearId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public DataByRegion() {
    }
}