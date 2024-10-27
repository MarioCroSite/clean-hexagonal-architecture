package com.mario.paystream;

import com.mario.paystream.application.domain.model.Account;
import com.mario.paystream.application.domain.model.Account.AccountId;
import com.mario.paystream.application.domain.model.Money;
import com.mario.paystream.application.port.out.LoadAccountPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SendMoneySystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoadAccountPort loadAccountPort;


    @Test
    @Sql("SendMoneySystemTest.sql")
    void sendMoney() {

        Money initialSourceBalance = sourceAccount().calculateBalance();
        Money initialTargetBalance = targetAccount().calculateBalance();

        ResponseEntity response = whenSendMoney(
                sourceAccountId(),
                targetAccountId(),
                transferredAmount());

        then(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        then(sourceAccount().calculateBalance())
                .isEqualTo(initialSourceBalance.minus(transferredAmount()));

        then(targetAccount().calculateBalance())
                .isEqualTo(initialTargetBalance.plus(transferredAmount()));
    }

    private Account sourceAccount() {
        return loadAccount(sourceAccountId());
    }

    private Account targetAccount() {
        return loadAccount(targetAccountId());
    }

    private AccountId sourceAccountId() {
        return new AccountId(1L);
    }

    private AccountId targetAccountId() {
        return new AccountId(2L);
    }

    private Account loadAccount(AccountId accountId) {
        return loadAccountPort.loadAccount(
                accountId,
                LocalDateTime.now());
    }

    private ResponseEntity whenSendMoney(
            AccountId sourceAccountId,
            AccountId targetAccountId,
            Money amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Void> request = new HttpEntity<>(null, headers);

        return restTemplate.exchange(
                "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}",
                HttpMethod.POST,
                request,
                Object.class,
                sourceAccountId.getValue(),
                targetAccountId.getValue(),
                amount.getAmount());
    }

    private Money transferredAmount() {
        return Money.of(500L);
    }
}
