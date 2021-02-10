package ua.com.verdysh.payments.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    @Column(nullable = false)
    private Date timestamp;
    @Column(nullable = false)
    private Double amount;
    private String reason;
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Account payer;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Account recipient;

    public Payment() { super(); }

    public Payment(Date timestamp, Double amount, String reason, Account payer, Account recipient) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.reason = reason;
        this.payer = payer;
        this.recipient = recipient;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Account getPayer() {
        return payer;
    }

    public void setPayer(Account payer) {
        this.payer = payer;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }
}
