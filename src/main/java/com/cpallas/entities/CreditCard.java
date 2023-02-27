package com.cpallas.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "credit_card_number")
    private Long creditCardNumber;
    @Column(name = "pin_code")
    private Integer pin;
    private Integer ccv;
    @Column(name = "credit_balance")
    private Integer creditBalance;
    @Column(name = "amount_balance")
    private Integer amountBalance;
    @Column(name = "current_balance")
    private Integer currentBalance;
    @Column(name = "minimum_payment")
    private Integer minimumPayment;
    @Column(name = "monthly_payment")
    private Integer monthlyPayment;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private String status;
    @ManyToOne
    private User user;
}
