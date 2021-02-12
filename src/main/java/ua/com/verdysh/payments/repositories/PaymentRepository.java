package ua.com.verdysh.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.verdysh.payments.domain.Account;
import ua.com.verdysh.payments.domain.Payment;

import java.util.Collection;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "select pay from Payment pay where pay.payer = :x and pay.recipient = :y order by pay.timestamp desc")
    Collection<Payment> getPaymentsByPayerAndRecipient(@Param("x") Account payerId, @Param("y") Account recipientId);

}
