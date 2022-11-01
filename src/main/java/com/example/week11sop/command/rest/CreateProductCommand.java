package com.example.week11sop.command.rest;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CreateProductCommand {

    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String productId;
}
