package com.CorrelApp.demo.classesMath;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class BigRegionRepo {
    @Getter
    private static List<BigRegion> bigRegionList = new ArrayList<>();

    public static void Add(BigRegion bigRegion) { bigRegionList.add(bigRegion); }

    public static BigRegion findById(long id) {
        for (BigRegion bigRegion : bigRegionList)
            if (bigRegion.getId() == id)
                return bigRegion;
        return null;
    }

    public static String findRegionNameById(long id) {
        for (BigRegion br : bigRegionList) {
            if (br.getId() == id)
                return br.getName();
            else if (br.findById(id) != null) {
                return br.findById(id).getName();
            }
        }
        return null;
    }
}
