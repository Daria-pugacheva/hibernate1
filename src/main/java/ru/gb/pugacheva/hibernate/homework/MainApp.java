package ru.gb.pugacheva.hibernate.homework;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {
        SessionFactory sessionFactory =new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductDao productDao = new ProductDao(sessionFactory);
        Product newProductToAdd = new Product(7L,"Bread",70);
        Product newProductToUpdate = new Product(1L,"IceCream",100);

       // System.out.println(productDao.findAll());
       // System.out.println(productDao.findById(2L));
        //productDao.deleteById(2L);
       // productDao.saveOrUpdate(newProductToAdd);
        productDao.saveOrUpdate(newProductToUpdate);

    }
}
