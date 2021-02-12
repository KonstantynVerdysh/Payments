package ua.com.verdysh.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.verdysh.payments.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
