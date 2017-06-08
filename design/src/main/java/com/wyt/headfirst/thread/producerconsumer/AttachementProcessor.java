package com.wyt.headfirst.thread.producerconsumer;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Darcy
 *         Created by Administrator on 2017/5/8.
 */
public class AttachementProcessor {
    private final String ATTACHMENT_STORE_BASE_DIR="/home/viscent/tmp/attachments/";
    private final Channel<File> channel = new BlockingQueueChannel<>(new ArrayBlockingQueue<File>(2000));

}
