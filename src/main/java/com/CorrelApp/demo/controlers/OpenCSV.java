package com.CorrelApp.demo.controlers;

import com.CorrelApp.demo.classesMath.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

@Controller
public class OpenCSV {
    @GetMapping("/services")
    public String openCSV(Model model) throws Exception
    {
        String path;
        String[] lines;

        // !!! params.csv !!! - работает
        path = "./src/main/resources/static/params.csv";
        lines = getFromFile(path);                                  // добываем массив строк

        for (int i = 1; i < lines.length; i++) {                    // i = 1, так 0 - это заголовки
            String[] fields = lines[i].split(";");                  // строки бьём на массив
            ParamRepo.add(new ParamByStatistic(
                    Long.parseLong(noQuo(fields[0])),                   // переводим число в строке в long
                    noQuo(fields[2])                                // noQuo - принимает строку и возвращает её без кавычек
            ));
        }

        // !!! regions.csv !!! - работает
        path = "./src/main/resources/static/regions.csv";
        lines = getFromFile(path);

        // большие регионы - работает
        for (int i = 1; i < lines.length; i++) {                    // i = 1, так 0 - это заголовки
            String[] fields = lines[i].split(";");
            if (Long.parseLong(noQuo(fields[1])) == 0) {
                BigRegionRepo.add(new BigRegion(
                        Long.parseLong(noQuo(fields[0])),
                        noQuo(fields[3])
                ));
            }
        }

        // 2) регионы - работает
        for (int i = 1; i < lines.length; i++) {
            String[] fields = lines[i].split(";");
            BigRegion br = BigRegionRepo.getById(
                    Long.parseLong(noQuo(fields[1]))
            );
            if (br != null)
                br.add(new Region(
                        Long.parseLong(noQuo(fields[0])),
                        Long.parseLong(noQuo(fields[1])),
                        noQuo(fields[3])
                ));
        }

        // !!! data.csv !!! -  работает
        path = "./src/main/resources/static/data.csv";
        lines = getFromFile(path);

        for (int i = 1; i < lines.length; i++) {
            String[] fields = lines[i].split(";");
            DataRepo.add(new DataByRegion(
                    Long.parseLong(noQuo(fields[0])),
                    Long.parseLong(noQuo(fields[1])),
                    Long.parseLong(noQuo(fields[2])),
                    Double.parseDouble(noQuo(fields[3]))
            ));
        }

        // мешаем всё в одно
        for (DataByRegion d : DataRepo.getDataList()) {
            String paramName = ParamRepo.getById(d.getParamId()).getName();
            String regionName = BigRegionRepo.getRegionNameById(d.getRegionId());

            SuperiorData sd = SuperiorDataRepo.findByParamNameAndRegionName(paramName, regionName);
            if (sd == null) {
                sd = new SuperiorData(paramName, regionName);
                SuperiorDataRepo.add(sd);
            }

            sd.add(new SpaceValue(d.getYearId(), d.getValue()));
        }

        // check
        /*for (SuperiorData sd : SuperiorDataRepo.getSuperiorDataList()) {
            System.out.println("Param name: " + sd.getParamName() +
                    " Region name: " + sd.getRegionName() +
                    "\nYEARS and VALUES:");
            model.addAttribute("Spec",sd.getSpecValueList());
            for (SpecValue sv : sd.getSpecValueList())
                System.out.println("\tYear: " + sv.getYear() +
                        " Value: " + sv.getValue());

        }*/

        for (SuperiorData sdMain : SuperiorDataRepo.getSuperiorDataList()) {
            List<Double> x = new ArrayList<>();

            for (SpaceValue sv : sdMain.getSpecValueList())
                x.add(sv.getValue());

            for (SuperiorData sd : SuperiorDataRepo.getSuperiorDataList()) {
                if (sd != sdMain & sd.getParamName() != sdMain.getParamName() &
                        sdMain.getType() != sd.getType()) {
                    List<Double> y = new ArrayList<>();

                    for (SpaceValue sv : sd.getSpecValueList())
                        y.add(sv.getValue());

                    double r;
                    try {
                        r = Correlation.correlateByPirson(x, y);
                    }
                    catch (NumberFormatException e) {
                        r = -99999;
                    }

                    if (abs(r) >= 0.9 & abs(r) <= 1)
                        System.out.println("Корреляция элементов " + sdMain.getParamName() +
                                " в " + sdMain.getRegionName() +
                                " и " + sd.getParamName() +
                                " в " + sd.getRegionName() +
                                " = " + r);
                }

            }
        }

        model.addAttribute("SuperiorData", SuperiorDataRepo.getSuperiorDataList());
        return "services";
    }


    // принимает путь к файлу csv и возвращает массив строк
    private static String[] getFromFile(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(",");
        String text = "";
        while (sc.hasNext()) {
            text += sc.next();
        }
        sc.close();
        return text.split("\n");
    }

    // нет кавычкам (No quotes) - принимает строку и удаляет кавычки
    private static String noQuo(String string) {
        if (string.split("\"").length > 1)
            return string.split("\"")[1];
        return string.split("\"")[0];
    }
}