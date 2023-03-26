package com.cpallas.config;

import com.cpallas.entities.BankAccount;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Permission;
import com.cpallas.entities.Role;
import com.cpallas.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;

@Configuration
@ComponentScan(basePackages = "com.cpallas")
public class ApplicationConfiguration {

    @Bean
    public org.hibernate.cfg.Configuration configuration() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(Permission.class);
        configuration.addAnnotatedClass(CreditCard.class);
        configuration.addAnnotatedClass(BankAccount.class);

        return configuration;
    }

    @Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration) {
        return configuration.configure().buildSessionFactory();
    }

    @Bean
    public EntityManager entityManager(SessionFactory sessionFactory) {
        return (EntityManager) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
    }

    @PreDestroy
    public void destroy(SessionFactory sessionFactory) {
        sessionFactory.close();
    }
}
