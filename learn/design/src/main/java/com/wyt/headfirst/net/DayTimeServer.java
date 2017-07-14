package com.wyt.headfirst.net;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author Darcy
 *         Created by Administrator on 2017/2/14.
 */
public class DayTimeServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(13)) {
            while (true) {
                try (Socket connection = server.accept()) {
                    Writer writer = new OutputStreamWriter(connection.getOutputStream());
                    Date date = new Date();
                    writer.write(date.toString() + "\r\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
