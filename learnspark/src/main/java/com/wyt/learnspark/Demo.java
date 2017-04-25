package com.wyt.learnspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Int;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Darcy
 *         Created by Administrator on 2017/4/22.
 */
public class Demo {
    public static void main(String[] args) {
        String master = "local";
        JavaSparkContext sc = new JavaSparkContext(
                master, "basicavg", System.getenv("SPARK_HOME"), System.getenv("JARS"));


        Function<Integer, CombineAvgCount> createAcc = x -> new CombineAvgCount(x, 1);
        Function2<CombineAvgCount, Integer, CombineAvgCount> addAndCount = (a, x) -> {
            a.total += x;
            a.num += 1;
            return a;
        };
        Function2<CombineAvgCount, CombineAvgCount, CombineAvgCount> combine = (a, b) -> {
            a.total += b.total;
            a.num += b.num;
            return a;
        };
        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4, 4, 2, 3));
        JavaPairRDD<Integer, Integer> pairRDD1 = sc.parallelizePairs(Arrays.asList(
                new Tuple2<>(1, 2),
                new Tuple2<>(2, 2),
                new Tuple2<>(3, 2),
                new Tuple2<>(1, 3),
                new Tuple2<>(2, 5),
                new Tuple2<>(3, 6),
                new Tuple2<>(4, 7)));
    /*    JavaPairRDD<Integer, Integer> pairRDD = rdd.mapToPair((x) -> new Tuple2<>(x, 1));
        List<Tuple2<Integer, Integer>> collect = pairRDD.collect();
        JavaPairRDD<Integer, Integer> integerIntegerJavaPairRDD = pairRDD.reduceByKey((a, b) -> a + b);
        List<Tuple2<Integer, Integer>> collect1 = integerIntegerJavaPairRDD.collect();
        CombineAvgCount initial = new CombineAvgCount(0, 0);*/
        JavaPairRDD<Integer, CombineAvgCount> avgCountJavaPairRDD = pairRDD1.combineByKey(createAcc, addAndCount, combine);
        Map<Integer, CombineAvgCount> integerCombineAvgCountMap = avgCountJavaPairRDD.collectAsMap();
        for (Map.Entry<Integer, CombineAvgCount> entry : integerCombineAvgCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().avg());
        }
       /* javaPairRDD1.collect().forEach(stringIntegerTuple2 -> {
            System.out.println(stringIntegerTuple2._1() + ": " + stringIntegerTuple2._2());
        });*/
      /*  List<String> list = new ArrayList<>();
        list.add("sdfalskfj");
        list.add("sdfalskfj");
        list.add("sdfalskfj");
        list.add("dfaewadfad");
        list.add("dfaewadfad");
        list.add("dfaewadfad");
        list.add("dfaewadfad");
        JavaRDD<String> parallelize = sc.parallelize(list);
        JavaPairRDD<String, Integer> stringIntegerJavaPairRDD = parallelize.mapToPair(s -> new Tuple2<>(s, 1)).reduceByKey((a, b) -> a + b);

        stringIntegerJavaPairRDD.collect().forEach(stringIntegerTuple2 -> {
            System.out.println(stringIntegerTuple2._1() + ": " + stringIntegerTuple2._2());
        });*/

       /* List<Tuple2<Integer, Integer>> tuple2s = new ArrayList<>();
        tuple2s.add(new Tuple2<>(1, 2));
        tuple2s.add(new Tuple2<>(3, 4));
        tuple2s.add(new Tuple2<>(3, 6));
        JavaPairRDD<Integer, Integer> tuplesRDD = sc.parallelizePairs(tuple2s);
        PairFunction<String, String, String> keyData = (PairFunction<String, String, String>) s -> new Tuple2<>(s.split(" ")[0], s);
        Function<Tuple2<String, String>, Boolean> longWordFilter = v1 -> v1._2().length() < 20;*/

    }
}
