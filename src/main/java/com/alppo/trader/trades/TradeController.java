package com.alppo.trader.trades;

import com.alppo.trader.trades.dto.CreateTradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trades")
public class TradeController {
    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService){
        this.tradeService = tradeService;
    }

    @GetMapping
    public List<Trade> getTrades(){
        return this.tradeService.getTrades();
    }

    @PostMapping("/create")
    public ResponseEntity<Trade> createTrade(@RequestBody CreateTradeDto requestDto)
    {
        Trade trade = this.tradeService.createTrade(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(trade);
    }
}
