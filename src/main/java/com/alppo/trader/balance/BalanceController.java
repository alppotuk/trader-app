package com.alppo.trader.balance;

import com.alppo.trader.balance.dto.BalanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ldap.Control;

@RestController
@RequestMapping("api/v1/balances")
public class BalanceController {
    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }

    @GetMapping
    public Page<BalanceDto> getBalances(
            @RequestParam(defaultValue = "0") int index,
            @RequestParam(defaultValue = "10") int size
    ) {
        return balanceService.getBalances(index, size);
    }

    @GetMapping("/all")
    public Page<BalanceDto> getAllBalances() {
        return balanceService.getAllBalances();
    }
}
