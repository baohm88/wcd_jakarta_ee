package com.t2404e.wellcometojavaweb.entity;

import com.t2404e.wellcometojavaweb.entity.helper.ProductEnum;

import java.util.Calendar;
import java.util.Date;

public class Product {
    private long id;
    private String name;
    private String description;
    private String content;
    private double price;
    private String thumbnail;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private ProductEnum status;

    public Product() {this.id =  Calendar.getInstance().getTimeInMillis();}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public ProductEnum getStatus() {
        return status;
    }

    public void setStatus(ProductEnum status) {
        this.status = status;
    }
}
