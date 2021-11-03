package com.krymlov.xmlparser.transformer;

import com.krymlov.xmlparser.object.Inhabitant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLFromCode {

    public static void main(String[] args) {
        Inhabitant inhabitant1 = Inhabitant.getInstance("Гусак Сергій Васильович", "19", "Факультет кала", "Кафедра кокаина", "10курс", "Петра гвинта 23", "-100");
        Inhabitant inhabitant2 = Inhabitant.getInstance("Гусак Вася Васильович", "21", "Факультет кала", "Кафедра кофеина", "5курс", "Петра гвинта 26", "-100");
        List<Inhabitant> list = new ArrayList<>();
        list.add(inhabitant1);
        list.add(inhabitant2);
        XMLFromCode.create("e:\\Users\\Danil\\Desktop\\KNU2020\\OOP\\Lab2\\src\\main\\resources\\tempdb.xml", list);
    }

    public static void create(String resourcesPath, List<Inhabitant> list){

        File file = new File(resourcesPath);

        StringBuilder stringBuilderXml = new StringBuilder();

        stringBuilderXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        stringBuilderXml.append("<?xml-stylesheet type=\"\" ?>");
        stringBuilderXml.append("<inhabitants type=\"root\">");

        for (int i = 0; i < list.size(); i++){
            stringBuilderXml.append("<inhabitant id=\"" + i + "\">");
            stringBuilderXml.append("<fullname>" + list.get(i).getFullname() + "</fullname>");
            stringBuilderXml.append("<age>" + list.get(i).getAge() + "</age>");
            stringBuilderXml.append("<faculty>" + list.get(i).getFaculty() + "</faculty>");
            stringBuilderXml.append("<cathedra>" + list.get(i).getCathedra() + "</cathedra>");
            stringBuilderXml.append("<grade>" + list.get(i).getGrade() + "</grade>");
            stringBuilderXml.append("<homeplace>" + list.get(i).getHomeplace() + "</homeplace>");
            stringBuilderXml.append("<payment>" + list.get(i).getPayment() + "</payment>");
            stringBuilderXml.append("</inhabitant>");
        }

        stringBuilderXml.append("</inhabitants>");
        System.out.println(stringBuilderXml.toString());

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.toString()), "UTF-8"));
            writer.write(stringBuilderXml.toString());
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
