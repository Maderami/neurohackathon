package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class ParamRepo {
    @Getter
    private static List<ParambyStatistic> parambyStatisticList = new ArrayList<>();

    public static void Add(ParambyStatistic parambyStatistic) { parambyStatisticList.add(parambyStatistic); }

    public static ParambyStatistic findById(long id) {
        for (ParambyStatistic parambyStatistic : parambyStatisticList)
            if (parambyStatistic.getId() == id)
                return parambyStatistic;
        return null;
    }

    public static List<ParambyStatistic> getParambyStatisticList() {
        return parambyStatisticList;
    }

    public static void setParambyStatisticList(List<ParambyStatistic> parambyStatisticList) {
        ParamRepo.parambyStatisticList = parambyStatisticList;
    }
}