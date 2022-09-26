package com.andersen.banking.meeting_impl.util;

import javax.transaction.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferUtil {

    public static Integer setTransactionalStatus(boolean value) {
        return value ? Status.STATUS_COMMITTING : Status.STATUS_ROLLING_BACK;
    }
}
