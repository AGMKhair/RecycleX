package com.example.recyclerx.Model;

public class ProductData {

    private int imgId;
    private String name;
    private String quantity;
    private String price;

    public ProductData(int imgId, String name, String quantity, String price) {
        this.imgId = imgId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
