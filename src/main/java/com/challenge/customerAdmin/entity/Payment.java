package com.challenge.customerAdmin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable=false)
    @JsonIgnoreProperties(value="payments")
    private Customer customer;
    @Column(nullable = false)
    private String method;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private LocalDateTime date;

}
