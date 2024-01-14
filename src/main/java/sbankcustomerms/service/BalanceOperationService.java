package sbankcustomerms.service;

import sbankcustomerms.kafka.payload.BalanceOperationPayload;

public interface BalanceOperationService {

    boolean addLog(BalanceOperationPayload payload);

}
