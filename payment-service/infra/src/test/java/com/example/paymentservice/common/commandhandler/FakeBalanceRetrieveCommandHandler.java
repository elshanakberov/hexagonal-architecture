package com.example.paymentservice.common.commandhandler;

import com.example.commons.commandhandler.CommandHandler;
import com.example.paymentservice.balance.command.BalanceRetrieve;
import com.example.paymentservice.balance.model.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Primary
@ConditionalOnProperty(name = "commandhandler.enabled", havingValue = "false", matchIfMissing = true)
public class FakeBalanceRetrieveCommandHandler implements CommandHandler<BalanceRetrieve, Balance> {

    private static final Long BALANCE_NOT_FOUND_ACCOUNT_ID = 6661L;

    private static final List<Long> FAILING_IDS = List.of(
            BALANCE_NOT_FOUND_ACCOUNT_ID
    );

    @Override
    public Balance handle(BalanceRetrieve balanceRetrieve) {
        failedBalanceRetrieveScenario(balanceRetrieve);
        return successfulBalanceRetrieveScenario(balanceRetrieve);
    }

    private Balance successfulBalanceRetrieveScenario(BalanceRetrieve balanceRetrieve) {
        if (!FAILING_IDS.contains(balanceRetrieve.getAccountId())){
            return Balance.builder()
                    .id(1L)
                    .accountId(balanceRetrieve.getAccountId())
                    .amount(BigDecimal.valueOf(10.0))
                    .build();
        }
        throw new RuntimeException("uncovered test scenario occurred");
    }

    private void failedBalanceRetrieveScenario(BalanceRetrieve balanceRetrieve) {
        if (balanceRetrieve.getAccountId().equals(BALANCE_NOT_FOUND_ACCOUNT_ID)){
            throw new RuntimeException("paymentapi.balance.notFound");
        }
    }
}
