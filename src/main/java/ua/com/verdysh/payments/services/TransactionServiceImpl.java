package ua.com.verdysh.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.verdysh.payments.domain.Account;
import ua.com.verdysh.payments.domain.Payment;
import ua.com.verdysh.payments.exceptions.ValidationException;
import ua.com.verdysh.payments.services.interfaces.ITransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isAmountPresent(Payment payment) {
        return payment.getPayer().getBalance() >= payment.getAmount();
    }

    @Override
    public void addToRecipientAccount(Payment payment) {
        Account account = payment.getRecipient();
        account.setBalance(account.getBalance() + payment.getAmount());
        accountService.save(account);
    }

    @Override
    public void removeFromPayerAccount(Payment payment) {
        if (isAmountPresent(payment)) {
            Account payer = payment.getPayer();
            payer.setBalance(payer.getBalance() - payment.getAmount());
            accountService.save(payer);
        } else {
            throw new ValidationException("Insufficient funds");
        }
    }

    @Override
    public void transfer(Payment payment) {

        Long payerId = payment.getPayer().getAccountId();
        Long recipientId = payment.getRecipient().getAccountId();

        if (payerId.equals(recipientId)) {
            throw new ValidationException("Impossible operation: account id must be different");
        }

        removeFromPayerAccount(payment);
        paymentService.save(payment);
        payment.setStatus("ok");
        addToRecipientAccount(payment);
    }

}
