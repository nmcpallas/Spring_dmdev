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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Entity
@Table(name = "client")
public class Client {

    @Id
    private Integer id;
    private String name;
    private String surname;
    @Column(name = "bank_account_number")
    private Long bankAccountNumber;
    @Column(name = "credit_card_number")
    private Long creditCardNumber;
}
