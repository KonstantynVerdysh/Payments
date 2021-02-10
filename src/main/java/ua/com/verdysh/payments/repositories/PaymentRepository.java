package ua.com.verdysh.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.verdysh.payments.domain.Payment;

import java.util.Collection;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select pay from Payment pay where pay.payer.accountId = :x and pay.recipient.accountId = :y order by pay.timestamp desc")
    public Collection<Payment> getPaymentsByPayerAndRecipient(@Param("x") Long payerId, @Param("y") Long recipientId);

}
