package ua.com.verdysh.payments.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.verdysh.payments.domain.Account;
import ua.com.verdysh.payments.domain.Customer;
import ua.com.verdysh.payments.domain.Views;
import ua.com.verdysh.payments.exceptions.ValidationException;
import ua.com.verdysh.payments.services.AccountService;
import ua.com.verdysh.payments.services.CustomerService;

import java.util.Collection;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @JsonView(Views.CustomerWithOnlyId.class)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        try {
            customerService.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException("Error. Account number already exists");
        }
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping
    public Collection<Customer> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping(value = "/{id}/accounts", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @JsonView(Views.CustomerAccounts.class)
    public Collection<Account> getAccounts(@PathVariable("id") Long customerId) {
        return customerService.findById(customerId).getAccounts();
    }

}
