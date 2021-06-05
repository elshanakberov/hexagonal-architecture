package com.example.paymentservice.adapters.balance.rest;

import com.example.paymentservice.balance.BalanceRetrieveCommandHandler;
import com.example.paymentservice.balance.command.BalanceRetrieve;
import com.example.paymentservice.adapters.balance.rest.dto.BalanceResponse;
import com.example.paymentservice.balance.model.Balance;
import com.example.paymentservice.common.commandhandler.CommandHandler;
import com.example.paymentservice.common.rest.BaseController;
import com.example.paymentservice.common.rest.Response;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/balances")
public class BalanceController extends BaseController {

    private final CommandHandler<BalanceRetrieve, Balance> balanceRetrieveCommandHandler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<BalanceResponse> retrieve(@RequestParam("accountId") Long accountId){
        var balance = balanceRetrieveCommandHandler.handle(BalanceRetrieve.from(accountId));
        log.info("Balance is retrieved for account {} as {}", accountId, balance);
        return respond(BalanceResponse.fromModel(balance));
    }



}

