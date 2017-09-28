package test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.List;

/**
 * Created by 王炳智 on 2017/9/28.
 */
public class ManyTables {
    public static void main(String[] args) {
        poqieneilianjie();
    }
    //hql右连接 没有迫切右连接
    public static void youlianjie(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query q = session.createQuery("from Customer c right outer join c.setLinkMan");

            List list = q.list();
            for (int i = 0; i <list.size() ; i++) {
                System.out.println(list.get(i));
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    //hql左连接
    public static void zuolianjie(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query q = session.createQuery("from Customer c left outer join c.setLinkMan");

            List list = q.list();
            for (int i = 0; i <list.size() ; i++) {
                System.out.println(list.get(i));
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //hql迫切左连接
    public static void poqiezuolianjie(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query q = session.createQuery("from Customer c left outer join fetch c.setLinkMan");

            List list = q.list();
            for (int i = 0; i <list.size() ; i++) {
                System.out.println(list.get(i));
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }




    //hql迫切内连接 返回对象
    public static void poqieneilianjie(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query q = session.createQuery("from Customer c inner join fetch c.setLinkMan");

            List list = q.list();
            for (int i = 0; i <list.size() ; i++) {
                System.out.println(list.get(i));
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }


    //hql内连接 返回数组
    public static void neilianjie(){
            SessionFactory sessionFactory = null;
            Session session = null;
            Transaction transaction = null;
            try{
                sessionFactory = HibernateUtils.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();

                Query q = session.createQuery("from Customer c inner join c.setLinkMan");

                List list = q.list();

                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
            }finally {
                session.close();
                sessionFactory.close();
            }
    }
}
