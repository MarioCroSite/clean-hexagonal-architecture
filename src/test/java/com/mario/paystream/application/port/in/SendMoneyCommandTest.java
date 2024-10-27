package com.mario.paystream.application.port.in;

import com.mario.paystream.application.domain.model.Account.AccountId;
import com.mario.paystream.application.domain.model.Money;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class SendMoneyCommandTest {

    @Test
    public void validationOk() {
        new SendMoneyCommand(
                new AccountId(42L),
                new AccountId(43L),
                new Money(new BigInteger("10")));
        // no exception
    }

    @Test
    public void moneyValidationFails() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new SendMoneyCommand(
                    new AccountId(42L),
                    new AccountId(43L),
                    new Money(new BigInteger("-10")));
        });
    }

    @Test
    public void accountIdValidationFails() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new SendMoneyCommand(
                    new AccountId(42L),
                    null,
                    new Money(new BigInteger("10")));
        });
    }

}
