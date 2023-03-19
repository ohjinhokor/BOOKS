package jpabook.jpashop;

import jpabook.jpashop.domain.*;

import javax.management.OperationsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

//            Order order = new Order();
////            order.addOrderItem(new OrderItem());
//            em.persist(order);
//
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//
//            em.persist(orderItem);
//
//            Order order = new Order();
//            em.persist(order);
//
//            Delivery delivery = new Delivery();
//            delivery.setOrder(order);
//            em.persist(delivery);
//
////            Order order2 = new Order();
////            em.persist(order2);
//
//            Delivery delivery2 = new Delivery();
////            delivery2.setOrder(order2);
//
//            em.persist(delivery2);
//

//            System.out.println("delivery2.getOrder().getId() = " + delivery2.getOrder().getId());


            Movie movie = new Movie();
            movie.setDirector("director");
            movie.setActor("bbb");
            movie.setName("바람과 함께");
            em.persist(movie);


            tx.commit();

        } catch(Exception e) {
            System.out.println("여기로");
            tx.rollback();
        }
        finally{
            em.close();
        }
        emf.close();
    }
}