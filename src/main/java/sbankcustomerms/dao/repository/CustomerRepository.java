package sbankcustomerms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbankcustomerms.dao.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCif(String cif);

}
