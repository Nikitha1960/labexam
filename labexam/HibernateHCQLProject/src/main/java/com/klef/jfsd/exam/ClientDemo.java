package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
    	Configuration configuration = new Configuration();
    	configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
    	configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/labexam");
    	configuration.setProperty("hibernate.connection.username", "root");
    	configuration.setProperty("hibernate.connection.password", "Nikitha@1904");
    	configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	configuration.setProperty("hibernate.show_sql", "true");
    	configuration.setProperty("hibernate.format_sql", "true");
    	configuration.setProperty("hibernate.hbm2ddl.auto", "update");
    	configuration.addAnnotatedClass(Customer.class);

    	SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john@example.com");
        customer1.setAge(30);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("jane@example.com");
        customer2.setAge(25);
        customer2.setLocation("California");

        session.persist(customer1);
        session.persist(customer2);
        List<Customer> customers = session.createQuery("FROM Customer", Customer.class).getResultList();
        System.out.println("Customer List:");
        for (Customer c : customers) {
            System.out.println(c.getName() + " - " + c.getEmail());
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
