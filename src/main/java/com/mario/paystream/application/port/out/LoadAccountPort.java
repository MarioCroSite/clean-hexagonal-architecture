package com.mario.paystream.application.port.out;

import com.mario.paystream.application.domain.model.Account;
import com.mario.paystream.application.domain.model.Account.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
