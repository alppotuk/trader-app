package com.alppo.trader.trades;

import com.alppo.trader.orders.Order;
import com.alppo.trader.orders.OrderRepository;
import com.alppo.trader.trades.dto.CreateTradeDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;
    private final OrderRepository orderRepository;
    @Autowired
    public TradeService(TradeRepository traderRepository,
                        OrderRepository orderRepository){
        this.tradeRepository = traderRepository;
        this.orderRepository = orderRepository;
    }

    public List<Trade> getTrades(){
        return this.tradeRepository.findAll();
    }

    public Trade createTrade(CreateTradeDto createTradeDto){
        Order openingOrder = this.orderRepository.findById(createTradeDto.getOpeningOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + createTradeDto.getOpeningOrderId()));
        Order closingOrder = this.orderRepository.findById(createTradeDto.getClosingOrderId())
                .orElseThrow(() ->  new EntityNotFoundException("Order not found with id: " + createTradeDto.getOpeningOrderId()));

        Trade trade = new Trade(openingOrder, closingOrder);

        return this.tradeRepository.save(trade);

    }
}
