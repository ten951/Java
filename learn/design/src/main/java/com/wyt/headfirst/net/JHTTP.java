package com.wyt.headfirst.net;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Darcy
 *         Created by Administrator on 2017/2/15.
 */
public class JHTTP {
    private static final Logger logger = Logger.getLogger(JHTTP.class.getCanonicalName());
    public static final int NUM_THREADS = 50;
    public static final String INDEX_FILE = "index.html";
    private final File rootDirectory;
    private final int port;

    public JHTTP(File rootDirectory, int port) throws IOException {
        if (!rootDirectory.isDirectory()) {
            throw new IOException(rootDirectory + " does not exist as a directory");
        }
        this.rootDirectory = rootDirectory;
        this.port = port;
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Document Root: " + rootDirectory);
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request);
                    pool.submit(r);
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Error accepting connection", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        run("D:\\Scala",80);
       /* File docroot;
        try {
            docroot = new File(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java JHTTP docroot port");
            return;
        }
        int port;
        try {
            port = Integer.parseInt(args[1]);
            if (port < 0 || port > 65535) port = 80;

        } catch (RuntimeException e) {
            port = 80;
        }
        try {
            JHTTP webserver = new JHTTP(docroot, port);
            webserver.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server could not start", e);
        }*/
    }
    private static void run(String root,int port) {
        File docroot;
        try {
            docroot = new File(root);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java JHTTP docroot port");
            return;
        }
        try {
            if (port < 0 || port > 65535) port = 80;

        } catch (RuntimeException e) {
            port = 80;
        }
        try {
            JHTTP webserver = new JHTTP(docroot, port);
            webserver.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server could not start", e);
        }
    }
}
