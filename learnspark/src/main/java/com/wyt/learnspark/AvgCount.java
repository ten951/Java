package com.wyt.learnspark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 聚合操作(自定义的返回值类型)
 * @author Darcy
 *         Created by Administrator on 2017/4/21.
 */
public class AvgCount implements Serializable {
    public AvgCount(int total, int num) {
        this.total = total;
        this.num = num;
    }

    public int total;
    public int num;

    public double avg() {
        return total / (double) num;
    }

    public static void main(String[] args) {
        Function2<AvgCount, Integer, AvgCount> addAndCount =
                (Function2<AvgCount, Integer, AvgCount>) (a, x) -> {
                    a.total += x;
                    a.num += 1;
                    return a;
                };
        Function2<AvgCount, AvgCount, AvgCount> combine =
                (Function2<AvgCount, AvgCount, AvgCount>) (a, b) -> {
                    a.total += b.total;
                    a.num += b.num;
                    return a;
                };
        String master = "local";
        JavaSparkContext sc = new JavaSparkContext(
                master, "basicavg", System.getenv("SPARK_HOME"), System.getenv("JARS"));
        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4));
        AvgCount initial = new AvgCount(0, 0);
        AvgCount result = rdd.aggregate(initial, addAndCount, combine);
        System.out.println(result.avg());
    }

}
