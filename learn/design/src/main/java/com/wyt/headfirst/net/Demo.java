package com.wyt.headfirst.net;

import com.google.common.io.FileBackedOutputStream;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.stream.Stream;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/23.
 */
public class Demo {


    public <T extends Comparable<? super T>> T test(T f) {
        return null;
    }

    public static void downloadWeb(String url) {
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            try (InputStream raw = uc.getInputStream()) {
                InputStream buffer = new BufferedInputStream(raw);
                Reader reader = new InputStreamReader(buffer);
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) return 4;
        else if (address.length == 6) return 6;
        else return -1;
    }

    public static String getMacCyrillicString(InputStream in) throws IOException {
        Reader r = new InputStreamReader(in, "MacCyrillic");
        r = new BufferedReader(r, 1024);
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = r.read()) != -1) sb.append((char) c);
        return sb.toString();

    }

    public static void testURI(String uri) {
        try {
            URI u = new URI(uri);
            System.out.println("The URI is " + u);
            if (u.isOpaque()) {//不透明的
                System.out.println("This is an opaque URI.");
                System.out.println("This scheme is " + u.getScheme());
                System.out.println("This scheme specific part is " + u.getSchemeSpecificPart());
                System.out.println("This fragment ID is " + u.getFragment());
            } else {
                System.out.println("This is a hierarchical URI");
                System.out.println("This is a hierarchical URI" + u.getScheme());
                try {
                    u = u.parseServerAuthority();
                    System.out.println("u.getHost() = " + u.getHost());
                    System.out.println("u.getUserInfo() = " + u.getUserInfo());
                    System.out.println("u.getPort() = " + u.getPort());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                System.out.println("u.getPath() = " + u.getPath());
                System.out.println("u.getQuery() = " + u.getQuery());
                System.out.println("u.getFragment() = " + u.getFragment());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void read(String url) {
        InputStream in = null;
        try {
            URL u = new URL(url);
            in = u.openStream();
            in = new BufferedInputStream(in);
            Reader r = new InputStreamReader(in);
            int c;
            while ((c = r.read()) != -1) System.out.println((char) c);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void parseEncoding(String url) {
        String encoding = "ISO-8859-1";
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            String contentType = uc.getContentType();
            int encodingStart = contentType.indexOf("charset=");
            if (encodingStart != -1) {
                encoding = contentType.substring(encodingStart + 8);
            }
            InputStream in = new BufferedInputStream(uc.getInputStream());
            Reader r = new InputStreamReader(in, encoding);
            int c;
            while ((c = r.read()) != -1) {
                System.out.println((char) c);
            }
            r.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveBinaryFile(URL u) throws IOException {
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file");
        }
        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];//初始化一个于文件大小一致的数组
            int offset = 0;
            while (offset < contentLength) {//循环把数据写入数组
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1) break;//当读取完成后 跳出循环
                offset += bytesRead;//并记录偏移量
            }
            if (offset != contentLength) {
                throw new IOException("Only read" + offset + "bytes; Expected " + contentLength + " bytes");
            }
            String filename = u.getFile();
            filename = filename.substring(filename.lastIndexOf("/") + 1);
            System.out.println("filename = " + filename);
            try (OutputStream fout = new FileOutputStream(filename)) {
                fout.write(data);
                fout.flush();
            }

        }

    }

    public static void getAllHeaders(URL u) throws IOException {
        URLConnection uc = u.openConnection();
        for (int j = 1; ; j++) {
            String header = uc.getHeaderField(j);
            if (header == null) break;
            System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
        }
    }

    public Date getDateFromNetwork() throws IOException, ParseException {
        try (Socket socket = new Socket("time.nist.gov", 13)) {
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char) c);
            }
            return parseDate(time.toString());
        }
    }

    private static Date parseDate(String s) throws ParseException {
        String[] pieces = s.split(" ");
        String dateTime = pieces[1] + " " + pieces[2] + " UTC";
        DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
        return format.parse(dateTime);
    }

    public static void getSocketInfo(String host, int port) {
        try {
            Socket theSocket = new Socket(host, 80);
            System.out.println("Connected to " + theSocket.getInetAddress()
                    + " on port " + theSocket.getPort() + " from port "
                    + theSocket.getLocalPort() + " of " + theSocket.getLocalAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isOpen(ServerSocket ss) {
        return ss.isBound() && !ss.isClosed();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);

/*

        URL url = new URL("http://cn.bing.com/search?");
        try {
            URLConnection uc = url.openConnection();
            uc.setDoOutput(true);
            OutputStream raw = uc.getOutputStream();
            raw = new BufferedOutputStream(raw);
            OutputStreamWriter out = new OutputStreamWriter(raw, "UTF-8");
            out.write("q=nihao&qs=AS&pq=niha&sc=8-4&cvid=98B817C322374E73B3F2D276FA97A306&FORM=QBLH&sp=1");
            out.flush();
            out.close();

            InputStream inputStream = uc.getInputStream();
            String contentType = uc.getContentType();
            int i = contentType.indexOf("charset=");
            String encoding = "ISO-8859-1";
            if (i != -1) {
                encoding = contentType.substring(i + 8);
            }
            inputStream = new BufferedInputStream(inputStream);
            InputStreamReader r = new InputStreamReader(inputStream, encoding);
            int c;
            while ((c = r.read()) != -1) {
                System.out.println((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
           */
/* int c;
            while ((c = r.read()) != -1) {
                System.out.println((char) c);
            }*/


    }
}
