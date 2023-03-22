package com.api.eccom.model;

import jakarta.persistence.*;


@Entity
@Table(name = "invoice_details")
public class Invoice_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_detail_id;

    private int amount;
    private Double price;

    @Override
    public String toString() {
        return "Invoice_Details{" +
                "invoice_detail_id=" + invoice_detail_id +
                ", amount=" + amount +
                ", price=" + price +
                ", invoice=" + invoice +
                ", products=" + products +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    public Invoice_Details() {
    }


    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Long getInvoice_detail_id() {
        return invoice_detail_id;
    }

    public void setInvoice_detail_id(Long invoice_detail_id) {
        this.invoice_detail_id = invoice_detail_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
