package com.wyt.headfirst.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 适配器模式
 * 1 继承方式(由于Java的继承是稀缺资源 不建议使用)
 * 2 组合方式(就是持有原始对象,继承需求接口) 在原始的方法上包装一层
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/16.
 */
public class FileProperties implements FileIO {

    private Properties properties = new Properties();


    @Override
    public void readFormFile(String filename) throws IOException {
        properties.load(new FileInputStream(filename));
    }

    @Override
    public void writeToFile(String filename) throws IOException {
        properties.store(new FileOutputStream(filename), "written by FileProperties");
    }

    @Override
    public void setValue(String key, String value) {
        properties.setProperty(key, value);

    }

    @Override
    public String getValue(String key) {
        return (String) properties.getProperty(key, "");
    }

    public static void main(String[] args) {
        FileIO f = new FileProperties();
        try {
            f.readFormFile("D:\\file.txt");
            f.setValue("year", "2004");
            f.setValue("month", "4");
            f.setValue("day", "21");
            f.writeToFile("D:\\newFile.txt");
            System.out.println(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
