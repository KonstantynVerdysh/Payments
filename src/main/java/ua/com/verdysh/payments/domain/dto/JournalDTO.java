package ua.com.verdysh.payments.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import ua.com.verdysh.payments.domain.Views;

import java.time.LocalDateTime;

public class JournalDTO {

    public JournalDTO() { }

    @JsonView(Views.JournalRequest.class)
    private Long payerId;
    @JsonView(Views.JournalRequest.class)
    private Long recipientId;
    @JsonView(Views.JournalRequest.class)
    private Long sourceAccId;
    @JsonView(Views.JournalRequest.class)
    private Long destAccId;

    public JournalDTO(Long payerId, Long recipientId, Long sourceAccId, Long destAccId) {
        this.payerId = payerId;
        this.recipientId = recipientId;
        this.sourceAccId = sourceAccId;
        this.destAccId = destAccId;
    }

    public Long getPayerId() {
        return payerId;
    }
    @JsonProperty("payer_id")
    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Long getRecipientId() {
        return recipientId;
    }
    @JsonProperty("recipient_id")
    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getSourceAccId() {
        return sourceAccId;
    }
    @JsonProperty("source_acc_id")
    public void setSourceAccId(Long sourceAccId) {
        this.sourceAccId = sourceAccId;
    }

    public Long getDestAccId() {
        return destAccId;
    }
    @JsonProperty("dest_acc_id")
    public void setDestAccId(Long destAccId) {
        this.destAccId = destAccId;
    }

    @JsonView(Views.JournalResponse.class)
    private Long paymentId;
    @JsonView(Views.JournalResponse.class)
    private LocalDateTime timestamp;
    @JsonView(Views.JournalResponse.class)
    private String sourceAccNum;
    @JsonView(Views.JournalResponse.class)
    private String destAccNum;
    @JsonView(Views.JournalResponse.class)
    private Double amount;
    @JsonView(Views.JournalResponse.class)
    private Payer payer;
    @JsonView(Views.JournalResponse.class)
    private Recipient recipient;

    public JournalDTO(Long paymentId, LocalDateTime timestamp, String sourceAccNum, String destAccNum, Double amount, Payer payer, Recipient recipient) {
        this.paymentId = paymentId;
        this.timestamp = timestamp;
        this.sourceAccNum = sourceAccNum;
        this.destAccNum = destAccNum;
        this.amount = amount;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    @JsonProperty("timestamp")
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSourceAccNum() {
        return sourceAccNum;
    }
    @JsonProperty("src_acc_num")
    public void setSourceAccNum(String sourceAccNum) {
        this.sourceAccNum = sourceAccNum;
    }

    public String getDestAccNum() {
        return destAccNum;
    }
    @JsonProperty("dest_acc_num")
    public void setDestAccNum(String destAccNum) {
        this.destAccNum = destAccNum;
    }

    public Double getAmount() {
        return amount;
    }
    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Payer getPayer() {
        return payer;
    }
    @JsonProperty("payer")
    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Recipient getRecipient() {
        return recipient;
    }
    @JsonProperty("recipient")
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    @JsonView(Views.JournalResponse.class)
    public static class Payer {

        private String firstName;
        private String lastName;

        public Payer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }
        @JsonProperty("first_name")
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }
        @JsonProperty("last_name")
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @JsonView(Views.JournalResponse.class)
    public static class Recipient {

        private String firstName;
        private String lastName;

        public Recipient(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }
        @JsonProperty("first_name")
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }
        @JsonProperty("last_name")
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

}
