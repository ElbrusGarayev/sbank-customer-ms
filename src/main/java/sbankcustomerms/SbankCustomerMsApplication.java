package sbankcustomerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SbankCustomerMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbankCustomerMsApplication.class, args);
    }

}
