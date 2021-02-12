package ua.com.verdysh.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.verdysh.payments.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
