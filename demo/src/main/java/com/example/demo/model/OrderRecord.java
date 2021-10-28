package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderRecord {
    private String orderNo;
    private String productId;
    private LocalDate date;
    private double quantity;

}
