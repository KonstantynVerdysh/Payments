package ua.com.verdysh.payments.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDTO {

    Long sourceAccId;
    Long destAccId;
    Double amount;
    String reason;

    public PaymentDTO() {
    }

    public PaymentDTO(Long sourceAccId, Long destAccId, Double amount, String reason) {
        this.sourceAccId = sourceAccId;
        this.destAccId = destAccId;
        this.amount = amount;
        this.reason = reason;
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
}
