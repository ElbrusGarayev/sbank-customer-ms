package sbankcustomerms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sbankcustomerms.dao.entity.BalanceOperationLog;
import sbankcustomerms.dao.repository.BalanceOperationLogRepository;
import sbankcustomerms.kafka.payload.BalanceOperationPayload;
import sbankcustomerms.service.BalanceOperationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceOperationServiceImpl implements BalanceOperationService {

    private final BalanceOperationLogRepository logRepository;

    @Override
    public boolean addLog(BalanceOperationPayload payload) {
        Optional<BalanceOperationLog> optionalLog = logRepository.findByTransactionId(payload.getTransactionId());
        if (optionalLog.isEmpty()) {
            var balanceOperationLog = buildBalanceOperationLog(payload);
            logRepository.save(balanceOperationLog);
            return true;
        }
        return false;
    }

    private BalanceOperationLog buildBalanceOperationLog(BalanceOperationPayload payload) {
        return BalanceOperationLog.builder()
                .transactionId(payload.getTransactionId())
                .refundTransactionId(payload.getRefundTransactionId())
                .amount(payload.getAmount())
                .type(payload.getType())
                .cif(payload.getCif())
                .build();
    }

}
