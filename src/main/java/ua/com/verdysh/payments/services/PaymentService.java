package ua.com.verdysh.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.verdysh.payments.domain.Account;
import ua.com.verdysh.payments.domain.Payment;
import ua.com.verdysh.payments.repositories.PaymentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void save(Payment account) {
        paymentRepository.save(account);
    }

    public void saveAll(Collection<Payment> payments) {
        paymentRepository.saveAll(payments);
    }

    public Collection<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not find"));
    }

    /**
     * This method is used to get journal of payments between two account
     */
    public Collection<Payment> getPaymentsByPayerAndRecipient(Account payerId, Account recipientId) {
        return paymentRepository.getPaymentsByPayerAndRecipient(payerId, recipientId);
    }
}
