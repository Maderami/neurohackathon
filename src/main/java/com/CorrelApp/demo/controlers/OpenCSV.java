package com.CorrelApp.demo.controlers;

import com.CorrelApp.demo.classesMath.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
@Controller

public class OpenCSV {

    @PostMapping("/services")
    public   String openCSV(Model model) throws Exception
    {
        String path;
        String[] lines;

        // !!! params.csv !!!
        // работает
        path = "./src/main/resources/static/params.csv";
        lines = getFromFile(path);                                  // добываем массив строк

        for (int i = 1; i < lines.length; i++) {                    // i = 1, так 0 - это заголовки
            String[] fields = lines[i].split(";");                  // строки бьём на массив
            ParamRepo.Add(new ParambyStatistic(
                    Long.parseLong(noQuo(fields[0])),                   // переводим число в строке в long
                    noQuo(fields[2])                                // noQuo - принимает строку и возвращает её без кавычек
            ));
        }

        // check
        /*for (Param param : ParamRepo.findAll())
            System.out.println("Id: " + param.getId() + " Name: " + param.getName());*/

        // !!! regions.csv !!!
        // работает
        path = "./src/main/resources/static/regions.csv";
        lines = getFromFile(path);

        // 1) большие регионы
        // работает
        for (int i = 1; i < lines.length; i++) {                    // i = 1, так 0 - это заголовки
            String[] fields = lines[i].split(";");
            if (Long.parseLong(noQuo(fields[1])) == 0) {
                BigRegionRepo.Add(new BigRegion(
                        Long.parseLong(noQuo(fields[0])),
                        noQuo(fields[3])
                ));
            }
        }

        // 2) регионы
        // работает
        for (int i = 1; i < lines.length; i++) {
            String[] fields = lines[i].split(";");
            BigRegion br = BigRegionRepo.findById(
                    Long.parseLong(noQuo(fields[1]))
            );
            if (br != null)
                br.Add(new Region(
                        Long.parseLong(noQuo(fields[0])),
                        Long.parseLong(noQuo(fields[1])),
                        noQuo(fields[3])
                ));
        }

        // check
        /*for (BigRegion br : BigRegionRepo.getBigRegionList()) {
            System.out.println("Id: " + br.getId() + " Name: " + br.getName() + "\nREGIONS:");
            for (Region r : br.getRegionList())
                System.out.println("\tId: " + r.getId() + " Upid: " + r.getUpid() + " Name: " + r.getName());
        }*/

        // !!! data.csv !!!
        // работает
        path = "./src/main/resources/static/data.csv";
        lines = getFromFile(path);

        for (int i = 1; i < lines.length; i++) {
            String[] fields = lines[i].split(";");
            DataRepo.Add(new DataByRegion(
                    Long.parseLong(noQuo(fields[0])),
                    Long.parseLong(noQuo(fields[1])),
                    Long.parseLong(noQuo(fields[2])),
                    Double.parseDouble(noQuo(fields[3]))
            ));
        }

        // check
        /*for (Data d : DataRepo.getDataList())
            System.out.println("Param_id: " + d.getParamId() +
                    " Region_id: " + d.getRegionId() +
                    " Year_id:" + d.getYearId() +
                    " Value: " + d.getValue());*/

        // мешаем всё в одно
        for (DataByRegion d : DataRepo.getDataList()) {
            String paramName = ParamRepo.findById(d.getparamid()).getName();
            String regionName = BigRegionRepo.findRegionNameById(d.getRegionId());

            /*
            for (BigRegion br : BigRegionRepo.getBigRegionList()) {
                if (br.getId() == d.getRegionId()) {
                    regionName = br.getName();
                    break;
                }
                else if (br.findById(d.getRegionId()) != null) {
                    regionName = br.findById(d.getRegionId()).getName();
                    break;
                }
            }*/

            SuperiorData sd = SuperiorDataRepo.findByParamNameAndRegionName(paramName, regionName);
            if (sd == null) {
                sd = new SuperiorData(paramName, regionName);
                SuperiorDataRepo.add(sd);
            }

            sd.Add(new SpaceValue(d.getYearId(), d.getValue()));
        }

        // check
        for (SuperiorData sd : SuperiorDataRepo.getSuperiorDataList()) {
            System.out.println("Param name: " + sd.getParamName() +
                    " Region name: " + sd.getRegionName() +
                    "\nYEARS and VALUES:");
            model.addAttribute("Spec",sd.getSpecValueList());
            for (SpaceValue sv : sd.getSpecValueList())
                System.out.println("\tYear: " + sv.getYear() +
                        " Value: " + sv.getValue());

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
    }//aboba
}