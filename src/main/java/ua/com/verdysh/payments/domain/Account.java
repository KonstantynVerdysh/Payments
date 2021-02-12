package ua.com.verdysh.payments.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.CustomerAccounts.class)
    private Long accountId;

    @Column(unique = true, length = 16)
    @JsonView(Views.CustomerAccounts.class)
    private String accountNum;

    @Column(nullable = false)
    @JsonView(Views.CustomerAccounts.class)
    private String accountType;

    @Column(nullable = false)
    @JsonView(Views.CustomerAccounts.class)
    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
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

    @JsonProperty("account_id")
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    @JsonProperty("account_num")
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountType() {
        return accountType;
    }

    @JsonProperty("account_type")
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    @JsonProperty("balance")
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

    @JsonProperty("sended_payments")
    public void setOutPayments(Collection<Payment> outPayments) {
        this.outPayments = outPayments;
    }

    public Collection<Payment> getInPayments() {
        return inPayments;
    }

    @JsonProperty("recieved_payments")
    public void setInPayments(Collection<Payment> inPayments) {
        this.inPayments = inPayments;
    }
}
