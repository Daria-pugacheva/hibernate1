package ru.gb.pugacheva.hibernate.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDao {
    private SessionFactory sessionFactory;

    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById (Long id){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class,id);
            System.out.println(product); // временно для проверки
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("from Product").getResultList();
            System.out.println(productList); // временно для проверки
            session.getTransaction().commit();
            return productList;
        }
    }

    public void deleteById (Long id){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
           // session.delete(findById(id)); наверное. не вариант?
           // session.createQuery("delete from Product p where p.id=" + id,Product.class); //просто запросом пока не получилось
            Product product = session.get(Product.class,id);
            session.delete(product);
            session.getTransaction().commit();
        }
        System.out.println(findAll());// для проверки, что удалили. временно
    }

    public Product saveOrUpdate (Product product){
        if(findById(product.getId())==null){
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                session.save(product);
                session.getTransaction().commit();
            }
        }else{
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                Product product1 = session.get(Product.class, product.getId());
                product1.setPrice(product.getPrice()); // всего два поля, поэтому не выясняю, что изменилось, для обновления точечными SQL-запросами, а просто обновляю все поля
                product1.setTitle(product.getTitle());
                session.getTransaction().commit();
            }
        }
        System.out.println(findAll()); // временно для проверки
        return product; // не очень понимаю смысл возврата продукта в этом методе... Но там в методичке
    }

}
