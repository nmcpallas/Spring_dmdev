package com.cpallas.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "credit_card_number", nullable = false)
    private Long creditCardNumber;
    @Column(name = "pin_code", nullable = false)
    private Integer pin;
    @Column(nullable = false)
    private Integer ccv;
    @Column(name = "credit_balance", nullable = false)
    private Integer creditBalance;
    @Column(name = "amount_balance", nullable = false)
    private Integer amountBalance;
    @Column(name = "current_balance", nullable = false)
    private Integer currentBalance;
    @Column(name = "minimum_payment", nullable = false)
    private Integer minimumPayment;
    @Column(name = "monthly_payment", nullable = false)
    private Integer monthlyPayment;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private String status;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;
}
