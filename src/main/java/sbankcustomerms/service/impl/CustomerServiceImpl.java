package sbankcustomerms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sbankcustomerms.dao.repository.CustomerRepository;
import sbankcustomerms.enums.BalanceOperationStatus;
import sbankcustomerms.exception.CustomerNotFoundException;
import sbankcustomerms.kafka.payload.BalanceOperationPayload;
import sbankcustomerms.kafka.payload.BalanceOperationResponsePayload;
import sbankcustomerms.kafka.producer.Producer;
import sbankcustomerms.mapper.CustomerMapper;
import sbankcustomerms.model.response.CustomerResponse;
import sbankcustomerms.service.CustomerService;
import sbankcustomerms.util.CustomerBalanceUpdater;

import static sbankcustomerms.enums.BalanceOperationStatus.ERROR;
import static sbankcustomerms.enums.BalanceOperationStatus.SUCCESS;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Producer producer;
    private final CustomerMapper mapper;

    @Override
    public CustomerResponse getCustomer(String cif) {
        return customerRepository.findByCif(cif)
                .map(mapper::toCustomerResponse)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public void updateBalance(BalanceOperationPayload payload) {
        customerRepository.findByCif(payload.getCif())
                .ifPresent(customer -> {
                    boolean isBalanceUpdated = CustomerBalanceUpdater
                            .updateBalance(customer, payload.getType(), payload.getAmount());
                    if (isBalanceUpdated) {
                        customerRepository.save(customer);
                        var responsePayload = buildBalanceOperationResponsePayload(SUCCESS, payload.getTransactionId());
                        producer.produce(payload.getResponseTopic(), responsePayload);
                    } else {
                        var responsePayload = buildBalanceOperationResponsePayload(ERROR, payload.getTransactionId());
                        producer.produce(payload.getResponseTopic(), responsePayload);
                    }
                });
    }

    private BalanceOperationResponsePayload buildBalanceOperationResponsePayload(
            BalanceOperationStatus status, String transactionId) {
        return BalanceOperationResponsePayload.builder()
                .transactionId(transactionId)
                .status(status)
                .build();
    }

}
