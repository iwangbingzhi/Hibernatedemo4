package test;

import entity.Customer;
import entity.LinkMan;
import org.hibernate.*;
import utils.HibernateUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by 王炳智 on 2017/9/28.
 */
public class ManyTables {
    public static void main(String[] args) {
        piliangzhuaqu();
    }
    //批量抓取
    public static void piliangzhuaqu(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(Customer.class);
            List<Customer> list = criteria.list();
            for (Customer c:list) {
                System.out.println(c.getCid()+":"+c.getCustName());
                Set<LinkMan>setLinkMan =  c.getSetLinkMan();
                for (LinkMan l: setLinkMan) {
                    System.out.println(l.getLkm_id()+":"+l.getLkm_name());
                }
            }

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }


    //Hibernate检索策略get and load
    public static void jiansuocelue(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

          /*  Customer customer = session.get(Customer.class,2);
            System.out.println(customer.getCid()+":"+customer.getCustName());*/

            Customer customer = session.load(Customer.class,2);
            System.out.println(customer.getCid());
            System.out.println(customer.getCustName());
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
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
