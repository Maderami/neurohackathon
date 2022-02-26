package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

public class SuperiorData {
    @Getter
    @Setter
    private String paramName;
    @Getter
    @Setter
    private String regionName;
    @Getter
    @Setter
    private List<SpaceValue> specValueList;

    public SuperiorData(String paramName, String regionName) {
        this.paramName = paramName;
        this.regionName = regionName;
        specValueList = new ArrayList<>();
    }

    public void Add(SpaceValue specValue) { specValueList.add(specValue); }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<SpaceValue> getSpecValueList() {
        return specValueList;
    }

    public void setSpecValueList(List<SpaceValue> specValueList) {
        this.specValueList = specValueList;
    }

    public SuperiorData() {
    }
}
