package com.wyt.headfirst.thinking.adapter;

import java.io.IOException;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/16.
 */
public interface FileIO {
    void readFormFile(String filename) throws IOException;
    void writeToFile(String filename) throws IOException;
    void setValue(String key,String value);
    String getValue(String key);
}
