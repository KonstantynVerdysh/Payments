package ua.com.verdysh.payments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.PaymentWithIdAndStatus.class, Views.PaymentWithOnlyId.class})
    private Long paymentId;

    @Column(nullable = false)
    private final LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private Double amount;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    @JsonIgnore
    private Account payer;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    @JsonIgnore
    private Account recipient;

//    @Transient
    @JsonView(Views.PaymentWithIdAndStatus.class)
    private String status;

    public Payment() { }

    public Payment(Account payer, Account recipient, Double amount, String reason) {
        this.amount = amount;
        this.reason = reason;
        this.payer = payer;
        this.recipient = recipient;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    @JsonProperty("payment_id")
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @JsonProperty("timestamp")
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
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

    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }
}
