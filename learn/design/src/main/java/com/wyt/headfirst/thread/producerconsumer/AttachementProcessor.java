package com.wyt.headfirst.thread.producerconsumer;

import com.wyt.headfirst.thread.twotermination.AbstractTerminatableThread;

import java.io.*;
import java.text.Normalizer;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Darcy
 * Created by Administrator on 2017/5/8.
 */
public class AttachementProcessor {
    private final String ATTACHMENT_STORE_BASE_DIR = "/home/viscent/tmp/attachments/";
    private final Channel<File> channel = new BlockingQueueChannel<>(new ArrayBlockingQueue<File>(2000));

    private final AbstractTerminatableThread indexingThread = new AbstractTerminatableThread() {
        @Override
        protected void doRun() throws Exception {
            File file = null;
            file = channel.take();

            try {
                indexFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                terminationToken.reservations.incrementAndGet();
            }

        }


        private void indexFile(File file) throws Exception {
            Random rnd = new Random();
            try {
                Thread.sleep(rnd.nextInt(100));
            } catch (InterruptedException e) {
                ;
            }
        }
    };

    public void init() {
        indexingThread.start();
    }

    public void shutdown() {
        indexingThread.terminate();
    }

    public void saveAttachment(InputStream in, String documentId, String originalFileName) throws IOException {
        File file = saveAsFile(in, documentId, originalFileName);
        try {
            channel.put(file);
        } catch (InterruptedException e) {
            ;
        }
        indexingThread.terminationToken.reservations.incrementAndGet();
    }

    private File saveAsFile(InputStream in, String documentId, String originalFileName) throws IOException {
        String dirName = ATTACHMENT_STORE_BASE_DIR + documentId;
        File dir = new File(dirName);
        dir.mkdir();
        File file = new File(dirName + "/" + Normalizer.normalize(originalFileName, Normalizer.Form.NFC));

        if (!dirName.equals(file.getCanonicalFile().getParent())) {
            throw new SecurityException("Invalid originalFileName:" + originalFileName);
        }

        BufferedOutputStream bos = null;
        BufferedInputStream bis = new BufferedInputStream(in);
        byte[] buf = new byte[2048];
        int len = -1;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            while ((len = bis.read()) > 0) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        } finally {
            bis.close();
            if (null != bos) {
                bos.close();
            }
        }
        return file;
    }

}
