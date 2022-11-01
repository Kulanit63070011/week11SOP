package com.example.week11sop.core.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreatedEvent {
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String productId;
}
