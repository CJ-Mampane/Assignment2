package com.example.scholarshelf;

public class Textbook {
    private String title;
    private String sellerName;
    private int copies;
    private double price;
    private String bankInfo;

    // Constructor
    public Textbook(String title, String sellerName, int copies, double price, String bankInfo) {
        this.title = title;
        this.sellerName = sellerName;
        this.copies = copies;
        this.price = price;
        this.bankInfo = bankInfo;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }

    public int getCopies() { return copies; }
    public void setCopies(int copies) { this.copies = copies; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getBankInfo() { return bankInfo; }
    public void setBankInfo(String bankInfo) { this.bankInfo = bankInfo; }
}
