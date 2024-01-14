package sbankcustomerms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbankcustomerms.model.response.CustomerResponse;
import sbankcustomerms.service.CustomerService;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{cif}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String cif) {
        CustomerResponse customer = customerService.getCustomer(cif);
        return ResponseEntity.ok(customer);
    }

}
