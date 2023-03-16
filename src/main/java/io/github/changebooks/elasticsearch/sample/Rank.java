package io.github.changebooks.elasticsearch.sample;

import java.io.Serializable;

/**
 * 用户等级
 *
 * @author changebooks@qq.com
 */
public class Rank implements Serializable {
    /**
     * 渠道
     */
    private Integer channel;

    /**
     * 级别
     */
    private Integer num;

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}
