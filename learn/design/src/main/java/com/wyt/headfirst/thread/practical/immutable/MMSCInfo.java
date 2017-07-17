package com.wyt.headfirst.thread.practical.immutable;

/**
 * 不可变模式
 * 1.类不能有子类,用final修饰
 * 2.所有属性私有,并且final修饰
 * 3.只有getter 去除所有setter
 * 4.只有一个可以创建完整对象的构造器
 * @author Darcy
 *         Created by Administrator on 2017/3/30.
 */
public final class MMSCInfo {
    //设备编号
    private final String deviceID;
    //彩信中心
    private final String url;
    //该彩信中心允许最大附近大小
    private final int maxAttachmentSizeInBytes;

    public MMSCInfo(String deviceID, String url, int maxAttachmentSizeInBytes) {
        this.deviceID = deviceID;
        this.url = url;
        this.maxAttachmentSizeInBytes = maxAttachmentSizeInBytes;
    }

    public MMSCInfo(MMSCInfo prototype) {
        this.deviceID = prototype.deviceID;
        this.url = prototype.url;
        this.maxAttachmentSizeInBytes = prototype.maxAttachmentSizeInBytes;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachmentSizeInBytes() {
        return maxAttachmentSizeInBytes;
    }
}
