package com.api.eccom.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created_at;
    private Double total;

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", total=" + total +
                ", client=" + client +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
