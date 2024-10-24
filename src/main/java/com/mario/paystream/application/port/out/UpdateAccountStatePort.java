package com.mario.paystream.application.port.out;

import com.mario.paystream.application.domain.model.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);
}
