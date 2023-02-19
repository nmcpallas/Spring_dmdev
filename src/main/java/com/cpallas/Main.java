package com.cpallas;

import com.cpallas.entities.Admin;
import com.cpallas.entities.BankAccount;
import com.cpallas.entities.Client;
import com.cpallas.entities.CreditCard;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Admin.class);
            configuration.addAnnotatedClass(BankAccount.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(CreditCard.class);
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) {
        
    }
}