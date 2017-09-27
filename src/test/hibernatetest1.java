package test;

import entity.Customer;
import entity.LinkMan;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by 王炳智 on 2017/9/27.
 */


public class hibernatetest1 {
    public static void main(String[] args) {
        reserchDemo4();
    }


    //对象导航查询和OID查询
    public static void reserchDemo(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            //查询cid等于2的客户，再查询这个客户的联系人
            Customer customer = session.get(Customer.class,2);
            //直接得到客户里联系人set集合
            Set<LinkMan> linkMen = customer.getSetLinkMan();
            for (LinkMan linkMan: linkMen) {
                System.out.println(linkMan);
            }
            System.out.println(linkMen.size());

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    //HQL查询 查询所有的客户
    public static void reserchAllDemo(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("from Customer");
            List<Customer> list = query.list();
            for (Customer customer: list) {
                System.out.println(customer);
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    //HQL查询 条件查询用户
    public static void reserchDemo3(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("from Customer where cid=? and custName=?");

            //设置条件值  向?设置值
            //第一个参数  int类型是？位置  ？位置从0开始
            //第二个位置  具体参数值
            //设置第一个？值
            query.setParameter(0,2);
            //设置第二个？值
            query.setParameter(1,"苹果");

            //调用方法得到结果
            List<Customer> list = query.list();

            for (Customer customer: list) {
                System.out.println(customer.getCid()+" " +customer.getCustName());
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    //HQL查询 模糊查询用户
    public static void reserchDemo4(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("from Customer where custName like ?");

            //设置条件值  向?设置值
            //第一个参数  int类型是？位置  ？位置从0开始
            //第二个位置  具体参数值
            //设置第一个？值
            query.setParameter(0,"%苹%");

            //调用方法得到结果
            List<Customer> list = query.list();

            for (Customer customer: list) {
                System.out.println(customer.getCid()+" " +customer.getCustName());
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
