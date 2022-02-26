package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import lombok.Setter;

public class SpaceValue {
    @Getter
    @Setter
    private long year;
    @Getter
    @Setter
    private double value;

    public SpaceValue(long year, double value) {
        this.year = year;
        this.value = value;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public SpaceValue() {
    }
}