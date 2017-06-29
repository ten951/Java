package com.wyt.headfirst.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * @author Darcy
 *         Created by Administrator on 2017/6/7.
 */
public class URLCat {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public void readHadoopFile(String hdfs) {
        InputStream in = null;
       // Configuration conf = new Configuration();
        try {
           /* FileSystem fs = FileSystem.get(URI.create(hdfs), conf);
            in = fs.open(new Path(hdfs));*/
            in = new URL(hdfs).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
        }
    }

    public static void main(String[] args) {
        URLCat cat = new URLCat();
        cat.readHadoopFile("hdfs://localhost/user/root/LICENSE.txt");
    }
}
