package sbankcustomerms.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sbankcustomerms.kafka.payload.BalanceOperationPayload;
import sbankcustomerms.service.BalanceOperationService;
import sbankcustomerms.service.CustomerService;

import static sbankcustomerms.util.ObjectMapperUtils.getEnhancedObjectMapperWithCamelCase;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    private final BalanceOperationService balanceOperationService;
    private final CustomerService customerService;
    private final ObjectMapper objectMapperWithCamelCase = getEnhancedObjectMapperWithCamelCase();

    @KafkaListener(topics = "${kafka.topics.update-balance}", groupId = "${kafka.configs.group-id}",
            containerFactory = "customerConcurrentKafkaListener")
    public void consume(@Payload String message) throws JsonProcessingException {
        log.info("consumed update balance message: {}", message);
        try {
            var payload = objectMapperWithCamelCase.readValue(message, BalanceOperationPayload.class);
            boolean isNew = balanceOperationService.addLog(payload);
            if (isNew) {
                customerService.updateBalance(payload);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while consuming balance operation message", ex);
            throw ex;
        }
    }

}
