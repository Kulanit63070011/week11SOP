package com.example.week11sop.command.rest;

import com.example.week11sop.core.event.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    //State
    @TargetAggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    //Command Handlers
    @CommandHandler
    public ProductAggregate(CreateProductCommand command){
        ///business logic
        if (command.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("price ห้าม น้อยกว่า0");
        }
        if (command.getTitle() == null || command.getTitle().isBlank()){
            throw new IllegalArgumentException("title ห้าม blank");
        }
        ProductCreatedEvent event = new ProductCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        //update state
        this.productId = event.getProductId();
        this.title = event.getTitle();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
    }
}
                                                                                                                                        