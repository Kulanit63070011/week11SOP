package com.example.week11sop.command.rest;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    private final CommandGateway gateway;

    @Autowired
    public ProductCommandController(CommandGateway gateway){
        this.gateway = gateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel model){
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .title(model.getTitle())
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .build();
        String result;
        try {
            result = gateway.sendAndWait(command);
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
