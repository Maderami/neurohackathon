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

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SuperiorData(String paramName, String regionName) {
        this.paramName = paramName;
        this.regionName = regionName;
        specValueList = new ArrayList<>();
        type = defineType();
    }

    public void add(SpaceValue specValue) { specValueList.add(specValue); }

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
    private String defineType() {
        for (String string :paramName.split(" "))
            if (string == "региональных" |
                    string == "США" |
                    string == "инновации")
                return "Государственная власть";
        return "Качество жизни людей";
    }
}
