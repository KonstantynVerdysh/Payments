package ua.com.verdysh.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.verdysh.payments.domain.Customer;
import ua.com.verdysh.payments.repositories.CustomerRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Collection<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not find"));
    }

}
