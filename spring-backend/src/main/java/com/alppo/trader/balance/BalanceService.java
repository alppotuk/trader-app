package com.alppo.trader.balance;

import com.alppo.trader.balance.dto.BalanceDto;
import com.alppo.trader.trades.Trade;
import com.alppo.trader.trades.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    private final TradeRepository tradeRepository;

    @Autowired
    public BalanceService(TradeRepository tradeRepository){
        this.tradeRepository = tradeRepository;
    }

    public Page<BalanceDto> getBalances(int index, int size) {
        Pageable pageable = PageRequest.of(index, size, Sort.by("closingOrder.createdAt").descending());
        Page<Trade> tradesPage = tradeRepository.findAll(pageable);
        return tradesPage.map(BalanceDto::new);
    }

    public Page<BalanceDto> getAllBalances() {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("closingOrder.createdAt").descending());
        Page<Trade> tradesPage = tradeRepository.findAll(pageable);
        return tradesPage.map(BalanceDto::new);
    }
}
