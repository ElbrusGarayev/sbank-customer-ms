package sbankcustomerms.service;

import sbankcustomerms.kafka.payload.BalanceOperationPayload;
import sbankcustomerms.model.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse getCustomer(String cif);

    void updateBalance(BalanceOperationPayload payload);

}
