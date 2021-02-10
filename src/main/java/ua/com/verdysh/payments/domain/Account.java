package ua.com.verdysh.payments.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    @JsonProperty("account_num")
    @Column(unique = true, length = 16)
    private String accountNum;
    @JsonProperty("account_type")
    @Column(nullable = false)
    private String accountType;
    @JsonProperty("balance")
    @Column(nullable = false)
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    @OneToMany(mappedBy = "payer", cascade = CascadeType.ALL)
    private Collection<Payment> outPayments;
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private Collection<Payment> inPayments;

    public Account() { }

    public Account(String accountNum, String accountType, Double balance) {
        this.accountNum = accountNum;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<Payment> getOutPayments() {
        return outPayments;
    }

    public void setOutPayments(Collection<Payment> outPayments) {
        this.outPayments = outPayments;
    }

    public Collection<Payment> getInPayments() {
        return inPayments;
    }

    public void setInPayments(Collection<Payment> inPayments) {
        this.inPayments = inPayments;
    }
}
