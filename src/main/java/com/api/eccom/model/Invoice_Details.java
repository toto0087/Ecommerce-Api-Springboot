package com.api.eccom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
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

}
