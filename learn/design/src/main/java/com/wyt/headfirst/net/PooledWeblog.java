package com.wyt.headfirst.net;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/28.
 */
public class PooledWeblog {
    private final static int NUM_THREADS = 4;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        LinkedList<LogEntry> results = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"))) {
            for (String entry = in.readLine(); entry != null; entry = in.readLine()) {
                LookupTask task = new LookupTask(entry);
                Future<String> future = executor.submit(task);
                LogEntry result = new LogEntry(entry, future);
                results.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (LogEntry result : results) {
            try {
                System.out.println("result = " + result.future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(result.original);
            }
        }
    }

    private static class LogEntry {
        String original;
        Future<String> future;

        LogEntry(String original, Future<String> future) {
            this.original = original;
            this.future = future;
        }
    }
}
