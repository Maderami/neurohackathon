package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class DataRepo {
    @Getter
    private static List<DataByRegion> dataList = new ArrayList<>();

    public static void add(DataByRegion data) { dataList.add(data); }

    public static DataByRegion findByParamId(long paramId) {
        for (DataByRegion data : dataList)
            if (data.getParamId() == paramId)
                return data;
        return null;
    }

    public static DataByRegion findByRegionId(long regionId) {
        for (DataByRegion data : dataList)
            if (data.getRegionId() == regionId)
                return data;
        return null;
    }

    public static DataByRegion findByYearId(long yearId) {
        for (DataByRegion data : dataList)
            if (data.getYearId() == yearId)
                return data;
        return null;
    }

    public static List<DataByRegion> getDataList() {
        return dataList;
    }

    public static void setDataList(List<DataByRegion> dataList) {
        DataRepo.dataList = dataList;
    }
}