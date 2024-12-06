package com.alppo.trader.orders;

import com.alppo.trader.orders.dto.CreateOrderDto;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders(){
        return this.orderService.getOrders();
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto requestDto){
        Order order = this.orderService.createOrder(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
