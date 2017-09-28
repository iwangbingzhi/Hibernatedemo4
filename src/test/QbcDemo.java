package test;

import entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtils;

import java.util.List;

/**
 * Created by 王炳智 on 2017/9/27.
 */
public class QbcDemo {
    public static void main(String[] args) {
        Qbctest6();
    }

    //离线查询 不再通过session创建对象
    public  static void Qbctest6(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            //Criteria criteria = session.createCriteria(Customer.class);
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);

            Criteria criteria = detachedCriteria.getExecutableCriteria(session);

            List<Customer> list = criteria.list();

            for (Customer c: list) {
                System.out.println(c.getCid()+":"+c.getCustName());
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //QBC统计查询
    public  static void Qbctest5(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(Customer.class);

            criteria.setProjection(Projections.rowCount());

            Object obj = criteria.uniqueResult();
            Long l = (Long) obj;
            int count = l.intValue();

            System.out.println(count);


            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }




    //分页查询   开始位置计算公式：(当前页-1)*每页记录数
    public  static void Qbctest4(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(Customer.class);

            criteria.setFirstResult(0);
            criteria.setMaxResults(3);
            List<Customer> list = criteria.list();

            for (Customer c: list) {
                System.out.println(c.getCid()+":"+c.getCustName());
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }



    //排序查询
    public  static void Qbctest3(){

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(Customer.class);

            criteria.addOrder(Order.desc("cid"));
            List<Customer> list = criteria.list();

            for (Customer c: list) {
                System.out.println(c.getCid()+":"+c.getCustName());
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }


    //QBC条件查询
    public  static void Qbctest2(){

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //创建对象
            Criteria criteria = session.createCriteria(Customer.class);

            //使用criteria对象里面的方法设置条件值
            //首先使用add方法，表示设置条件值
            //在add方法中使用类的方法实现条件设置
            /*criteria.add(Restrictions.eq("cid",2));
            criteria.add(Restrictions.eq("custName","苹果"));*/

            //模糊查询
            criteria.add(Restrictions.like("custName","%f%"));
            List<Customer> list = criteria.list();

            for (Customer c: list) {
                System.out.println(c);
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //QBC查询所有
    public  static void Qbctest(){

            SessionFactory sessionFactory = null;
            Session session = null;
            Transaction transaction = null;
            try {
                sessionFactory = HibernateUtils.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();


                //创建对象
                Criteria criteria = session.createCriteria(Customer.class);

                //调用方法得到对象
                List<Customer> list = criteria.list();

                for (Customer c: list) {
                    System.out.println(c.getCid()+","+c.getCustName());
                }
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
            }finally {
                session.close();
                sessionFactory.close();
        }
    }
}
