package com.mario.paystream.adapter.out.persistence;

import com.mario.paystream.application.domain.model.Account.AccountId;
import com.mario.paystream.application.port.out.AccountLock;
import org.springframework.stereotype.Component;

@Component
public class NoOpAccountLock implements AccountLock {

    @Override
    public void lockAccount(AccountId accountId) {
        // do nothing
    }

    @Override
    public void releaseAccount(AccountId accountId) {
        // do nothing
    }
}
