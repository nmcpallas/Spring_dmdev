package com.cpallas.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @Column(name = "credit_card_number")
    private Long creditCardNumber;
    @Column(name = "pin_code")
    private Integer pinCode;
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
    @Column(name = "date_start")
    private LocalDate dateStart;
    @Column(name = "date_end")
    private LocalDate dateEnd;
    private Status status;
}
