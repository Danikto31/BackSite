package org.example.backbase.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoodsRequestBody {

    public long getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("sellerId")
    public long getSellerId() {
        return sellerId;
    }

    @JsonProperty("cost")
    public int getCost() {
        return cost;
    }

    @JsonProperty("count")
    public int getCount() {
        return count;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("categories")
    public String getCategories() {
        return categories;
    }

    public long id;

    public String title;

    public long sellerId;

    public int cost;

    public int count;

    public String description;

    public String categories;

    public GoodsRequestBody(String title, long sellerId, int cost, int count, String description, String categories) {
        this.title = title;
        this.sellerId = sellerId;
        this.cost = cost;
        this.count = count;
        this.description = description;
        this.categories = categories;
    }


    public GoodsRequestBody(String title, long sellerId, int cost, int count){
        this.title = title;
        this.sellerId = sellerId;
        this.cost = cost;
        this.count = count;
    }

    public GoodsRequestBody(){}
}
