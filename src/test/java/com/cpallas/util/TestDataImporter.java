package com.cpallas.util;

import com.cpallas.entities.BankAccount;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Permission;
import com.cpallas.entities.Role;
import com.cpallas.entities.Status;
import com.cpallas.entities.User;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

import static com.cpallas.entities.RoleValue.ADMIN;
import static com.cpallas.entities.RoleValue.USER;

@UtilityClass
public class TestDataImporter {

    public void importData(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();

        Role admin = saveRole(session, ADMIN.getStatus());
        Role user = saveRole(session, USER.getStatus());

        User alex = saveUser(session, "Alex", "Sizov");
        User anay = saveUser(session, "Anay", "Chan");
        User ruslan = saveUser(session, "Ruslan", "Kamalov");
        User aisylu = saveUser(session, "Aisylu", "Mutakharova");
        User nail = saveUser(session, "Nail", "Mukminov");

        savePermission(session, "alex123", "123141", alex, admin);
        savePermission(session, "anay123", "123141", anay, user);
        savePermission(session, "ruslan123", "123141", ruslan, user);
        savePermission(session, "aisylu123", "123141", aisylu, user);
        savePermission(session, "nail123", "123141", nail, admin);

        BankAccount anyasBankAccount = saveBankAccount(session, 1L, 100, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), anay);
        BankAccount ruslansBankAccount = saveBankAccount(session, 2L, 100, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), ruslan);
        BankAccount aisylusBankAccount = saveBankAccount(session, 3L, 100, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), aisylu);

        saveCreditCard(session, 1L, 2010, 347, 50, 300, 250, null, null, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), Status.ACTIVE, anyasBankAccount);
        saveCreditCard(session, 2L, 2010, 347, 2, 300, 299, null, null, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), Status.ACTIVE, ruslansBankAccount);
        saveCreditCard(session, 3L, 2010, 347, 0, 300, 300, null, null, LocalDate.of(2000, 10, 20), LocalDate.of(2003, 10, 20), Status.ACTIVE, aisylusBankAccount);
    }

    private Role saveRole(Session session, String role) {
        Role entityRole = Role.builder()
                .role(role)
                .build();

        session.save(entityRole);

        return entityRole;
    }

    private User saveUser(Session session, String name, String surname) {
        User entityUser = User.builder()
                .name(name)
                .surname(surname)
                .build();

        session.save(entityUser);

        return entityUser;
    }

    private Permission savePermission(Session session, String login, String password, User user, Role role) {
        Permission entityPermission = Permission.builder()
                .login(login)
                .passowrd(password)
                .user(user)
                .role(role)
                .build();

        session.save(entityPermission);

        return entityPermission;
    }

    private BankAccount saveBankAccount(Session session, Long accountNumber, Integer balance, LocalDate startDate, LocalDate endDate, User user) {
        BankAccount entityBankAccount = BankAccount.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .startDate(startDate)
                .endDate(endDate)
                .user(user)
                .build();

        session.save(entityBankAccount);

        return entityBankAccount;
    }

    private CreditCard saveCreditCard(Session session, Long creditCardNumber, Integer pin, Integer ccv, Integer creditBalance, Integer amountBalance, Integer currentBalance, Integer minimumPayment, Integer monthlyPayment, LocalDate startDate, LocalDate endDate, Status status, BankAccount bankAccount) {
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

        session.save(entityCreditCard);

        return entityCreditCard;
    }
}
