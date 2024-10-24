package com.mario.paystream.application.port.in;

import com.mario.paystream.application.domain.model.Account.AccountId;
import com.mario.paystream.application.domain.model.Money;

public interface GetAccountBalanceUseCase {

    Money getAccountBalance(GetAccountBalanceQuery query);

    record GetAccountBalanceQuery(AccountId accountId) {
    }
}
