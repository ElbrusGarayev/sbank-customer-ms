package sbankcustomerms.kafka.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sbankcustomerms.enums.BalanceOperationType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceOperationPayload {

    private String transactionId;
    private String refundTransactionId;
    private String cif;
    private BigDecimal amount;
    private BalanceOperationType type;
    private String responseTopic;

}
