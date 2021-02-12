package ua.com.verdysh.payments.services.interfaces;

import ua.com.verdysh.payments.domain.Payment;

public interface ITransactionService {

    /**
     * This method is used to check the specified amount on the specified account
     */
    boolean isAmountPresent(Payment payment);

    /**
     * It is used to add the specified amount at the specified account
     */
    void addToRecipientAccount(Payment payment);

    /**
     * This method is used to remove the specified amount from the specified account
     */
    void removeFromPayerAccount(Payment payment);

    /**
     * This method is used to make a transfer between two account
     */
    void transfer(Payment payment);

}
