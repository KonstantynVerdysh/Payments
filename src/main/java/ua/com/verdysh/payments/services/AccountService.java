package ua.com.verdysh.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.verdysh.payments.domain.Account;
import ua.com.verdysh.payments.domain.Customer;
import ua.com.verdysh.payments.exceptions.ValidationException;
import ua.com.verdysh.payments.repositories.AccountRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void save(Account account) {
        accountRepository.save(account);
    }

    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not find"));
    }

    public void isCustomerAccount(Customer customer, Account account) {
        Collection<Long> customerAccIds = customer.getAccounts().stream()
                .map(Account::getAccountId)
                .collect(Collectors.toSet());
        if (!customerAccIds.contains(account.getAccountId())) {
            throw new ValidationException("Invalid relation for customer/account");
        }
    }

}
