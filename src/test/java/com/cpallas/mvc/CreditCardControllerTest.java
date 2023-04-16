package com.cpallas.mvc;

import com.cpallas.entities.dao.BaseIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.cpallas.dto.CreditCardCreateEditDto.Fields.amountBalance;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.bankAccountId;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.ccv;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.creditBalance;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.creditCardNumber;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.currentBalance;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.endDate;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.minimumPayment;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.monthlyPayment;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.pin;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.startDate;
import static com.cpallas.dto.CreditCardCreateEditDto.Fields.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class CreditCardControllerTest extends BaseIntegrationTest {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/cards"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("card/cards"))
                .andExpect(model().attributeExists("cards"))
                .andExpect(model().attribute("cards", hasSize(3)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/cards")
                        .param(creditCardNumber, "98")
                        .param(pin, "9809")
                        .param(ccv, "999")
                        .param(creditBalance, "0")
                        .param(amountBalance, "100")
                        .param(currentBalance, "100")
                        .param(minimumPayment, "0")
                        .param(monthlyPayment, "0")
                        .param(startDate, "2000-10-10")
                        .param(endDate, "2005-10-10")
                        .param(status, "active")
                        .param(bankAccountId, "1"))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrlPattern("/cards/{\\d}"));
    }
}
