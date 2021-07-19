package com.example.orderservice.order;

import com.example.commons.DomainComponent;
import com.example.commons.commandhandler.CommandHandler;
import com.example.orderservice.order.command.OrderCreate;
import com.example.orderservice.order.event.OrderCreatedEvent;
import com.example.orderservice.order.model.Order;
import com.example.orderservice.order.port.OrderCreatedEventPort;
import com.example.orderservice.order.port.OrderPort;
import com.example.orderservice.payment.command.PaymentCreate;
import com.example.orderservice.payment.model.Payment;
import com.example.orderservice.payment.port.PaymentPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@DomainComponent
@RequiredArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<OrderCreate,Order> {

    private final OrderPort orderPort;
    private final PaymentPort paymentPort;
    private final OrderCreatedEventPort orderCreatedEventPort;


    @Override
    public Order handle(OrderCreate orderCreate) {
        var payment = paymentPort.pay(buildPaymentCreate());
        var order = orderPort.createOrder(orderCreate);
        orderCreatedEventPort.publish(OrderCreatedEvent.from(order,payment));
        return order;
    }

    private PaymentCreate buildPaymentCreate() {
        return PaymentCreate.builder()
                .accountId(1L)
                .price(BigDecimal.valueOf(10))
                .referenceCode("ref")
                .build();
    }
}
