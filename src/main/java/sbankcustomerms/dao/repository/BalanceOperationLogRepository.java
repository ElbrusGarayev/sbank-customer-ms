package sbankcustomerms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbankcustomerms.dao.entity.BalanceOperationLog;
import sbankcustomerms.dao.entity.Customer;

import java.util.Optional;

public interface BalanceOperationLogRepository extends JpaRepository<BalanceOperationLog, Long> {

    Optional<BalanceOperationLog> findByTransactionId(String transactionId);

}
