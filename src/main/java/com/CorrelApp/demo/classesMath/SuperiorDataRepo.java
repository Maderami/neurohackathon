package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class SuperiorDataRepo {
    @Getter
    private static List<SuperiorData> superiorDataList = new ArrayList<>();

    public static void add(SuperiorData superiorData) { superiorDataList.add(superiorData); }

    public static SuperiorData findByParamNameAndRegionName(String paramName, String regionName) {
        for (SuperiorData superiorData : superiorDataList)
            if (superiorData.getParamName() == paramName
                    & superiorData.getRegionName() == regionName)
                return superiorData;
        return null;
    }

    public static List<SuperiorData> getSuperiorDataList() {
        return superiorDataList;
    }

    public static void setSuperiorDataList(List<SuperiorData> superiorDataList) {
        SuperiorDataRepo.superiorDataList = superiorDataList;
    }
}