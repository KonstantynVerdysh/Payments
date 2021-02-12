package ua.com.verdysh.payments.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.verdysh.payments.domain.Account;
import ua.com.verdysh.payments.domain.Customer;
import ua.com.verdysh.payments.domain.Payment;
import ua.com.verdysh.payments.domain.Views;
import ua.com.verdysh.payments.domain.dto.JournalDTO;
import ua.com.verdysh.payments.domain.dto.PaymentDTO;
import ua.com.verdysh.payments.services.AccountService;
import ua.com.verdysh.payments.services.CustomerService;
import ua.com.verdysh.payments.services.PaymentService;
import ua.com.verdysh.payments.services.interfaces.ITransactionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @JsonView(Views.PaymentWithOnlyId.class)
    public ResponseEntity<Payment> create(@RequestBody PaymentDTO paymentDto) {

        Long sourceAccId = paymentDto.getSourceAccId();
        Account payer = accountService.findById(sourceAccId);

        Long destAccId = paymentDto.getDestAccId();
        Account recipient = accountService.findById(destAccId);

        Payment payment = new Payment(payer, recipient, paymentDto.getAmount(), paymentDto.getReason());
        transactionService.transfer(payment);

        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @PostMapping(value = "/list",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @JsonView(Views.PaymentWithIdAndStatus.class)
    public ResponseEntity<Collection<Payment>> createList(@RequestBody Collection<PaymentDTO> paymentDto) {

        Collection<Payment> payments = paymentDto.stream()
                .map(this::processPayments)
                .collect(Collectors.toList());

        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    private Payment processPayments(PaymentDTO paymentDto) {

        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setReason(paymentDto.getReason());

        try {
            Long sourceAccId = paymentDto.getSourceAccId();
            Account payer = accountService.findById(sourceAccId);
            payment.setPayer(payer);

            Long destAccId = paymentDto.getDestAccId();
            Account recipient = accountService.findById(destAccId);
            payment.setRecipient(recipient);

            transactionService.transfer(payment);
        } catch (Exception e) {
            logger.error(e.getMessage());
            payment.setStatus("error");
        }
        return payment;
    }

    @PostMapping(value = "/journal",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @JsonView(Views.JournalResponse.class)
    public ResponseEntity<Collection<JournalDTO>> getPaymentJournal(@JsonView(Views.JournalRequest.class) @RequestBody JournalDTO journalDTO) {

        Customer payer = customerService.findById(journalDTO.getPayerId());
        Account sourceAcc = accountService.findById(journalDTO.getSourceAccId());

        Customer recipient = customerService.findById(journalDTO.getRecipientId());
        Account destAcc = accountService.findById(journalDTO.getDestAccId());

        accountService.isCustomerAccount(payer, sourceAcc);
        accountService.isCustomerAccount(recipient, destAcc);

        Collection<Payment> payments = paymentService.getPaymentsByPayerAndRecipient(sourceAcc, destAcc);
        Collection<JournalDTO> journalResponse = createJournalResponse(payments);

        return new ResponseEntity<>(journalResponse, HttpStatus.OK);
    }

    public Collection<JournalDTO> createJournalResponse(Collection<Payment> payments) {

        Collection<JournalDTO> journal = new ArrayList<>();
        for (Payment payment : payments) {
            Long paymentId = payment.getPaymentId();
            LocalDateTime timestamp = payment.getTimestamp();
            String sourceAccNum = payment.getPayer().getAccountNum();
            String destAccNum = payment.getRecipient().getAccountNum();
            Double amount = payment.getAmount();
            Customer payer = payment.getPayer().getCustomer();
            Customer recipient = payment.getRecipient().getCustomer();

            JournalDTO.Payer responsePayer = new JournalDTO.Payer(payer.getFirstName(), payer.getLastName());
            JournalDTO.Recipient responseRecipient = new JournalDTO.Recipient(recipient.getFirstName(), recipient.getLastName());

            JournalDTO journalDTO =
                    new JournalDTO(paymentId, timestamp, sourceAccNum, destAccNum, amount, responsePayer, responseRecipient);

            journal.add(journalDTO);
        }
        return journal;
    }

}
