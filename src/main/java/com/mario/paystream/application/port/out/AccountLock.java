package com.mario.paystream.application.port.out;

import com.mario.paystream.application.domain.model.Account.AccountId;

public interface AccountLock {

    void lockAccount(AccountId accountId);

    void releaseAccount(AccountId accountId);

}
