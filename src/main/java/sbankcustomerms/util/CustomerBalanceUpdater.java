package sbankcustomerms.util;

import sbankcustomerms.dao.entity.Customer;
import sbankcustomerms.enums.BalanceOperationType;

import java.math.BigDecimal;

public class CustomerBalanceUpdater {

    public static boolean updateBalance(Customer customer, BalanceOperationType type, BigDecimal amount) {
        return switch (type) {
            case TOP_UP -> topUp(customer, amount);
            case REFUND -> refund(customer, amount);
            case PURCHASE -> purchase(customer, amount);
        };
    }

    private static boolean topUp(Customer customer, BigDecimal amount) {
        customer.setBalance(customer.getBalance().add(amount));
        return true;
    }

    private static boolean refund(Customer customer, BigDecimal amount) {
        customer.setBalance(customer.getBalance().add(amount));
        return true;
    }

    private static boolean purchase(Customer customer, BigDecimal amount) {
        if (customer.getBalance().compareTo(amount) >= 0) {
            customer.setBalance(customer.getBalance().subtract(amount));
            return true;
        }
        return false;
    }

}