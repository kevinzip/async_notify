package com.qbao.pay.notify.entity;

import java.util.Date;

/**
 * 对应数据库表：async_notify_detail
 * 
 * @author zouchanglong
 *
 */
public class AsyncNotifyDetail extends PageEntity{
    private Long asyncNotifyId;

    private String channelCode;

    private String outTradeNo;

    private Byte asyncType;

    private Byte asyncStatus;

    private String notifyUrl;

    private String notifyMessage;

    private Date createTime;

    private Date modifyTime;

    public Long getAsyncNotifyId() {
        return asyncNotifyId;
    }

    public void setAsyncNotifyId(Long asyncNotifyId) {
        this.asyncNotifyId = asyncNotifyId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public Byte getAsyncType() {
        return asyncType;
    }

    public void setAsyncType(Byte asyncType) {
        this.asyncType = asyncType;
    }

    public Byte getAsyncStatus() {
        return asyncStatus;
    }

    public void setAsyncStatus(Byte asyncStatus) {
        this.asyncStatus = asyncStatus;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public String getNotifyMessage() {
        return notifyMessage;
    }

    public void setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage == null ? null : notifyMessage.trim();
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
}