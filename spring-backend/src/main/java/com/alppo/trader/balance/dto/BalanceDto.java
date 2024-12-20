package com.alppo.trader.balance.dto;

import com.alppo.trader.trades.Trade;

import java.time.LocalDateTime;

public class BalanceDto {
    private Double balance;
    private Double pnl;
    private LocalDateTime dateTime;

    public BalanceDto(Trade trade){
        this.balance  = trade.getClosingOrder().getBalanceAmount();
        this.dateTime = trade.getClosingOrder().getCreatedAt();
        this.pnl     = trade.getPnl();
    }

    public Double getPnl() {
        return pnl;
    }

    public void setPnl(Double pnl) {
        this.pnl = pnl;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
