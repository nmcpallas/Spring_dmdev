package com.cpallas.util;

import com.cpallas.entities.BankAccount;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Permission;
import com.cpallas.entities.Role;
import com.cpallas.entities.Status;
import com.cpallas.entities.User;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static com.cpallas.entities.RoleValue.ADMIN;
import static com.cpallas.entities.RoleValue.USER;

@UtilityClass
public class TestDataImporter {

    public void importData(EntityManager entityManager) {
        Role admin = saveRole(entityManager, ADMIN.getRole());
        Role user = saveRole(entityManager, USER.getRole());

        User alex = saveUser(entityManager, "Alex", "Sizov");
        User anay = saveUser(entityManager, "Anay", "Chan");
        User ruslan = saveUser(entityManager, "Ruslan", "Kamalov");
        User aisylu = saveUser(entityManager, "Aisylu", "Mutakharova");
        User nail = saveUser(entityManager, "Nail", "Mukminov");

        savePermission(entityManager, "alex123", "123141", alex, admin);
        savePermission(entityManager, "anay123", "123141", anay, user);
        savePermission(entityManager, "ruslan123", "123141", ruslan, user);
        savePermission(entityManager, "aisylu123", "123141", aisylu, user);
        savePermission(entityManager, "nail123", "123141", nail, admin);

        BankAccount anyasBankAccount = saveBankAccount(entityManager, 1L, 100, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), anay);
        BankAccount ruslansBankAccount = saveBankAccount(entityManager, 2L, 100, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), ruslan);
        BankAccount aisylusBankAccount = saveBankAccount(entityManager, 3L, 100, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), aisylu);

        saveCreditCard(entityManager, 1L, 2010, 347, 50, 300, 250, null, null, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), Status.ACTIVE, anyasBankAccount);
        saveCreditCard(entityManager, 2L, 2010, 347, 2, 300, 299, null, null, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), Status.ACTIVE, ruslansBankAccount);
        saveCreditCard(entityManager, 3L, 2010, 347, 0, 300, 300, null, null, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), Status.ACTIVE, aisylusBankAccount);
    }

    private Role saveRole(EntityManager entityManager, String role) {
        Role entityRole = Role.builder()
                .role(role)
                .build();

        entityManager.persist(entityRole);

        return entityRole;
    }

    private User saveUser(EntityManager entityManager, String name, String surname) {
        User entityUser = User.builder()
                .name(name)
                .surname(surname)
                .build();

        entityManager.persist(entityUser);

        return entityUser;
    }

    private Permission savePermission(EntityManager entityManager, String login, String password, User user, Role role) {
        Permission entityPermission = Permission.builder()
                .login(login)
                .passowrd(password)
                .user(user)
                .role(role)
                .build();

        entityManager.persist(entityPermission);

        return entityPermission;
    }

    private BankAccount saveBankAccount(EntityManager entityManager, Long accountNumber, Integer balance, LocalDate startDate, LocalDate endDate, User user) {
        BankAccount entityBankAccount = BankAccount.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .startDate(startDate)
                .endDate(endDate)
                .user(user)
                .build();

        entityManager.persist(entityBankAccount);

        return entityBankAccount;
    }

    private CreditCard saveCreditCard(EntityManager entityManager, Long creditCardNumber, Integer pin, Integer ccv, Integer creditBalance, Integer amountBalance, Integer currentBalance, Integer minimumPayment, Integer monthlyPayment, LocalDate startDate, LocalDate endDate, Status status, BankAccount bankAccount) {
        CreditCard entityCreditCard = CreditCard.builder()
                .creditCardNumber(creditCardNumber)
                .pin(pin)
                .ccv(ccv)
                .creditBalance(creditBalance)
                .amountBalance(amountBalance)
                .currentBalance(currentBalance)
                .minimumPayment(minimumPayment)
                .monthlyPayment(monthlyPayment)
                .startDate(startDate)
                .endDate(endDate)
                .status(status.getStatus())
                .bankAccount(bankAccount)
                .build();

        entityManager.persist(entityCreditCard);

        return entityCreditCard;
    }
}
