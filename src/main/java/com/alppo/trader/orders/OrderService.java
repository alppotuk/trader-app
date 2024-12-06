package com.alppo.trader.orders;

import com.alppo.trader.orders.dto.CreateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders(){
        return this.orderRepository.findAll();
    }

    public Order createOrder(CreateOrderDto createOrderDto){
        Order order = new Order();

        order.setSymbol(createOrderDto.getSymbol());
        order.setSide(createOrderDto.getSide());
        order.setBalanceAmount(createOrderDto.getBalanceAmount());
        order.setQuantity(createOrderDto.getQuantity());
        order.setLeverage(createOrderDto.getLeverage());

        return this.orderRepository.save(order);
    }

}
