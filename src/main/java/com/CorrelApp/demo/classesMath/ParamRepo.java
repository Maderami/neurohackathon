package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class ParamRepo {
    @Getter
    private static List<ParamByStatistic> paramByStatisticList = new ArrayList<>();

    public static void add(ParamByStatistic parambyStatistic) { paramByStatisticList.add(parambyStatistic); }

    public static ParamByStatistic getById(long id) {
        for (ParamByStatistic parambyStatistic : paramByStatisticList)
            if (parambyStatistic.getId() == id)
                return parambyStatistic;
        return null;
    }

    public static List<ParamByStatistic> getParamByStatisticList() {
        return paramByStatisticList;
    }

    public static void setParamByStatisticList(List<ParamByStatistic> paramByStatisticList) {
        ParamRepo.paramByStatisticList = paramByStatisticList;
    }
}