package com.largeproject.figmadesigns.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("coin")
    @Expose
    private String coin;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("updated")
    @Expose
    private Integer updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "CoinModel{" +
                "id='" + id + '\'' +
                ", coin='" + coin + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", updated=" + updated +
                '}';
    }
}
