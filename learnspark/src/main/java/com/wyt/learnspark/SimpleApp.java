package com.wyt.learnspark;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * @author Darcy
 *         Created by Administrator on 2016/10/14.
 */
public class SimpleApp {

    public static void main(String[] args) {
        String logFile = "d:\\catalina.out"; // Should be some file on your system
        SparkConf conf = new SparkConf().setAppName("Simple Application");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> inputRDD = sc.textFile(logFile);
        JavaRDD<String> successRDD = inputRDD
                .filter((Function<String, Boolean>) s -> s.contains("Response Code : 200"));
        JavaRDD<String> errorRDD = inputRDD
                .filter((Function<String, Boolean>) s -> s.contains("Response Code : 400"));
        JavaRDD<String> union = successRDD.union(errorRDD);
        System.out.println("union = " + union);
        System.out.println("Input had = " + inputRDD.count() + " concerning lines");
        System.out.println("Here are 10 examples: ");
        for (String s : inputRDD.take(10)) {
            System.out.println("s = " + s);
        }

        // long numAs = logData.filter((Function<String, Boolean>) s -> s.contains("a")).count();

        // long numBs = logData.filter((Function<String, Boolean>) s -> s.contains("b")).count();

        // System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }
}
