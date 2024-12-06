package com.alppo.trader.trades;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository
        extends JpaRepository<Trade, Long> {
}
