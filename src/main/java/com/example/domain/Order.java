package com.example.domain;

import java.util.Date;

public class Order {
    private int id;
    private String goodsName;
    private int goodsPrice;
    private int goodsNumber;
    private int goodsSource;
    private int payBackPrice;
    private int isPayBack;
    private int commission;
    private Date time;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public int getGoodsSource() {
        return goodsSource;
    }

    public void setGoodsSource(int goodsSource) {
        this.goodsSource = goodsSource;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getPayBackPrice() {
        return payBackPrice;
    }

    public void setPayBackPrice(int payBackPrice) {
        this.payBackPrice = payBackPrice;
    }

    public int getIsPayBack() {
        return isPayBack;
    }

    public void setIsPayBack(int isPayBack) {
        this.isPayBack = isPayBack;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsNumber=" + goodsNumber +
                ", goodsSource=" + goodsSource +
                ", payBackPrice=" + payBackPrice +
                ", isPayBack=" + isPayBack +
                ", commission=" + commission +
                ", time=" + time +
                ", userId=" + userId +
                '}';
    }
}
