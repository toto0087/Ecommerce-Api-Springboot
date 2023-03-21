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

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    public Invoice_Details() {
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
