package com.api.eccom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created_at;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @PrePersist
    private void prePersist() {
        created_at = LocalDateTime.now();
    }


}
