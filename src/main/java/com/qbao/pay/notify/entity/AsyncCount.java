package com.qbao.pay.notify.entity;

import java.util.Date;

/**
 * 对应数据库表：async_count
 * 
 * @author zouchanglong
 *
 */
public class AsyncCount {
    private Long asyncCountId;

    private Long asyncNotifyId;

    private Byte asynncTimes;

    private Byte openFlag;

    private Date createTime;

    private Date modifyTime;

    private Date sendTime;

    public Long getAsyncCountId() {
        return asyncCountId;
    }

    public void setAsyncCountId(Long asyncCountId) {
        this.asyncCountId = asyncCountId;
    }

    public Long getAsyncNotifyId() {
        return asyncNotifyId;
    }

    public void setAsyncNotifyId(Long asyncNotifyId) {
        this.asyncNotifyId = asyncNotifyId;
    }

    public Byte getAsynncTimes() {
        return asynncTimes;
    }

    public void setAsynncTimes(Byte asynncTimes) {
        this.asynncTimes = asynncTimes;
    }

    public Byte getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(Byte openFlag) {
        this.openFlag = openFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}