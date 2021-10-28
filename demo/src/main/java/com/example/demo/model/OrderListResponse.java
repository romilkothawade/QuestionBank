package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderListResponse {
    private String orderNo;
    private LocalDate createDate;
    private double quantity;
}
