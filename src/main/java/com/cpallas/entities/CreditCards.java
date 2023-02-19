package com.cpallas.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Entity
@Table(name = "credit_cards")
public class CreditCards {

    @Id
    private Integer id;
    @Column(name = "max_percent")
    private Integer maxPercent;
    @Column(name = "credit_card_number")
    private Long creditCardNumber;
    private Integer admin;
}
