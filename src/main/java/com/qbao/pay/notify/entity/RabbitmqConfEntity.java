package com.qbao.pay.notify.entity;

import java.util.Date;

/**
 * 对应数据库表：rabbitmq_conf
 * 
 * @author zouchanglong
 *
 */
public class RabbitmqConfEntity extends PageEntity{
    private Long mqId;

    private String exchangeName;

    private String queueName;

    private String routingKey;

    private Date createTime;

    public Long getMqId() {
        return mqId;
    }

    public void setMqId(Long mqId) {
        this.mqId = mqId;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName == null ? null : exchangeName.trim();
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName == null ? null : queueName.trim();
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey == null ? null : routingKey.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}