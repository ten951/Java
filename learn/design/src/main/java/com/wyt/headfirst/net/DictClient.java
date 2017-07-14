package com.wyt.headfirst.net;

import java.io.*;
import java.net.Socket;

/**
 * @author Darcy
 *         Created by Administrator on 2017/2/13.
 */
public class DictClient {
    public static final String SERVER = "dict.org";
    public static final int PORT = 2628;
    public static final int TIMEOUT = 15000;

    private static void define(String word, Writer writer, BufferedReader reader) throws IOException {
        writer.write("DEFINE eng-lat " + word + "\r\n");
        writer.flush();
        //System.out.println(reader.readLine());
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if (line.startsWith("250 ")) {
                return;
            } else if (line.startsWith("552 ")) {
                System.out.println("No definition found for " + word);
                return;
            } else if (line.matches("\\d\\d\\d .*")) continue;
            else if (line.trim().equals(".")) continue;
            else System.out.println(line);
        }
    }

    public static void writerServer(String word) {
        Socket socket = null;
        try {
            socket = new Socket(SERVER, PORT);
            socket.setSoTimeout(TIMEOUT);
            OutputStream out = socket.getOutputStream();
            Writer writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            define(word, writer, reader);
            writer.write("quit\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        writerServer("gold");
    }
}
