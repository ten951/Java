package com.wyt.headfirst.lambda;

import com.google.common.base.Function;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.T;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/8.
 */
public class Test {


    public static Integer cale(Integer para) {
        return para / 2;
    }

    public Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(Album::getMainMusician, Collectors.mapping(Album::getName, Collectors.toList())));
    }

    public static void process(Runnable r) {
        r.run();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* LongAdder adder = new LongAdder();
        LongAccumulator accumulator = new LongAccumulator((i, j) -> j - i, Long.MIN_VALUE);
        accumulator.accumulate(100);
        accumulator.accumulate(101);
        int i1 = accumulator.intValue();
        System.out.println("i1 = " + i1);
        adder.add(1);
        adder.increment();
        int i = adder.intValue();
        System.out.println("i = " + i);*/
      /*  CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> cale(50));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> cale(25));

        CompletableFuture<Void> fu = future1.thenCombine(future2, (i, j) -> (i + j))
                .thenApply(str -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        fu.get();*/

       /* CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> cale(50))
                .thenCompose(i -> CompletableFuture
                        .supplyAsync(() -> cale(i)))
                .thenApply(i -> Integer.toString(i))
                .thenApply(str -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        future.get();*/
      /*  int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        IntConsumer outprintln = System.out::println;
        IntConsumer errprintln = System.err::println;

        Arrays.stream(arr).forEach(outprintln.andThen(errprintln));*/
        List<Dish> menu = new ArrayList<>();

        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        int i = max.orElse(1);

        Integer[] num = {2, 34, 5};


        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        Map<String, Long> collect3 = transactions.stream()
                .map(Transaction::getTrader)
                .collect(Collectors.groupingBy(Trader::getName, Collectors.counting()));
        System.out.println("collect3 = " + collect3);

        List<Transaction> collect = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println("2011年交易 并按交易金额由小到大排序 = " + collect);

        List<String> collect1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("交易员都在哪些不同的城市工作过 = " + collect1);

        List<Trader> cambridge = transactions.stream()
                .map(Transaction::getTrader)//映射所有的人
                .filter(trader -> trader.getCity().equals("Cambridge"))//筛选出剑桥的
                .distinct()//去重
                .sorted(Comparator.comparing(Trader::getName))//按照名称排序
                .collect(Collectors.toList());
        System.out.println("所有来自剑桥的交易员,并按姓名排序 = " + cambridge);

        String collect2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())//映射所有的人
                .distinct()
                .sorted()
                .collect(Collectors.joining());

        System.out.println("返回交易员的姓名字符串,并按照字母顺序排序 = " + collect2);

        transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        Optional<Integer> reduce = transactions.stream().map(Transaction::getValue)
                .reduce(Integer::max);

        Optional<Transaction> min = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        //斐波那契元组序列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

    }
}
