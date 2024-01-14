package sbankcustomerms.kafka.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sbankcustomerms.enums.BalanceOperationStatus;
import sbankcustomerms.enums.BalanceOperationType;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceOperationResponsePayload {

    private BalanceOperationStatus status;
    private String transactionId;

}
