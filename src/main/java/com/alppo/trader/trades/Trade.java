package com.alppo.trader.trades;

import com.alppo.trader.orders.Order;
import jakarta.persistence.*;

@Entity
@Table(name = "trades")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "opening_order_id", referencedColumnName = "id", nullable = false)
    private Order openingOrder;

    @OneToOne
    @JoinColumn(name = "closing_order_id", referencedColumnName = "id", nullable = false)
    private Order closingOrder;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Integer leverage;

    @Column(nullable = false)
    private Double pnl; // profit & loss

    public Trade(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOpeningOrder() {
        return openingOrder;
    }

    public void setOpeningOrder(Order openingOrder) {
        this.openingOrder = openingOrder;
    }

    public Order getClosingOrder() {
        return closingOrder;
    }

    public void setClosingOrder(Order closingOrder) {
        this.closingOrder = closingOrder;
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

    public Double getPnl() {
        return pnl;
    }

    public void setPnl(Double pnl) {
        this.pnl = pnl;
    }
}
