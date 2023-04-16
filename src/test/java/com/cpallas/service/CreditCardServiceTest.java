package com.cpallas.service;

import com.cpallas.dto.CreditCardCreateEditDto;
import com.cpallas.dto.CreditCardReadDto;
import com.cpallas.entities.Status;
import com.cpallas.entities.dao.BaseIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class CreditCardServiceTest extends BaseIntegrationTest {

    private final CreditCardService service;

    @Test
    void findAll() {
        List<CreditCardReadDto> result = service.findAll();

        assertThat(result).hasSize(3);
    }

    @Test
    void findById() {
        Optional<CreditCardReadDto> result = service.findById(2);

        assertTrue(result.isPresent());
        result.ifPresent(user -> assertEquals(Status.ACTIVE.name(), user.getStatus()));
    }

    @Test
    void create() {
        CreditCardCreateEditDto expected = new CreditCardCreateEditDto(
                98L,
                9490,
                669,
                0,
                100,
                100,
                0,
                0,
                LocalDate.of(2000, 10, 10),
                LocalDate.of(2005, 10, 10),
                Status.ACTIVE.name(),
                3
        );

        CreditCardReadDto actual = service.create(expected);

        assertEquals(expected.getCreditCardNumber(), actual.getCreditCardNumber());
        assertEquals(expected.getPin(), actual.getPin());
        assertEquals(expected.getCcv(), actual.getCcv());
        assertEquals(expected.getCreditBalance(), actual.getCreditBalance());
        assertEquals(expected.getAmountBalance(), actual.getAmountBalance());
        assertEquals(expected.getMinimumPayment(), actual.getMinimumPayment());
        assertEquals(expected.getMonthlyPayment(), actual.getMonthlyPayment());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getBankAccountId(), actual.getBankAccountDto().id());
    }

    @Test
    void update() {
        CreditCardCreateEditDto expected = new CreditCardCreateEditDto(
                98L,
                9490,
                669,
                0,
                100,
                100,
                0,
                0,
                LocalDate.of(2000, 10, 10),
                LocalDate.of(2005, 10, 10),
                Status.ACTIVE.name(),
                3
        );

        Optional<CreditCardReadDto> actualOpt = service.update(1, expected);

        assertTrue(actualOpt.isPresent());

        CreditCardReadDto actual = actualOpt.get();

        assertEquals(expected.getCreditCardNumber(), actual.getCreditCardNumber());
        assertEquals(expected.getPin(), actual.getPin());
        assertEquals(expected.getCcv(), actual.getCcv());
        assertEquals(expected.getCreditBalance(), actual.getCreditBalance());
        assertEquals(expected.getAmountBalance(), actual.getAmountBalance());
        assertEquals(expected.getMinimumPayment(), actual.getMinimumPayment());
        assertEquals(expected.getMonthlyPayment(), actual.getMonthlyPayment());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getBankAccountId(), actual.getBankAccountDto().id());
    }

    @Test
    void delete() {
        boolean resultTrue = service.delete(1);
        boolean resultFalse = service.delete(-1);

        assertTrue(resultTrue);
        assertFalse(resultFalse);
    }
}
