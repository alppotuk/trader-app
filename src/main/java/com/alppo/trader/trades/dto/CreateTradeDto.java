package com.alppo.trader.trades.dto;

public class CreateTradeDto {
    private Long openingOrderId;
    private Long closingOrderId;

    public Long getOpeningOrderId() {
        return openingOrderId;
    }

    public void setOpeningOrderId(Long openingOrderId) {
        this.openingOrderId = openingOrderId;
    }

    public Long getClosingOrderId() {
        return closingOrderId;
    }

    public void setClosingOrderId(Long closingOrderId) {
        this.closingOrderId = closingOrderId;
    }
}
