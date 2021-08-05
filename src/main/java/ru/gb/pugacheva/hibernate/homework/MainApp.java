package ru.gb.pugacheva.hibernate.homework;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {
        SessionFactory sessionFactory =new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductDao productDao = new ProductDao(sessionFactory);
        System.out.println(productDao.findAll());

    }
}
