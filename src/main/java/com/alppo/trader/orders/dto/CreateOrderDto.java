package com.alppo.trader.orders.dto;

import com.alppo.trader.orders.Order;
import jakarta.persistence.*;

public class CreateOrderDto {
    private String symbol;
    private Order.Side side;
    private Double openingPrice;
    private Double balanceAmount;
    private Double quantity;
    private Integer leverage;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Order.Side getSide() {
        return side;
    }

    public void setSide(Order.Side side) {
        this.side = side;
    }

    public Double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(Double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getLeverage() {
        return leverage;
    }

    public void setLeverage(Integer leverage) {
        this.leverage = leverage;
    }

}
