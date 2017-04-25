package com.wyt.learnspark;

import java.io.Serializable;

/**
 * 基于键的聚合函数
 * @author Darcy
 *         Created by Administrator on 2017/4/24.
 */
public class CombineAvgCount implements Serializable {
    public CombineAvgCount(int total, int num) {
        this.total = total;
        this.num = num;
    }

    public int total;
    public int num;

    public double avg() {
        return total / (double) num;
    }

}
