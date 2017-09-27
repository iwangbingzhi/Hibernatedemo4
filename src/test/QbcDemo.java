package test;

import entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtils;

import java.util.List;

/**
 * Created by 王炳智 on 2017/9/27.
 */
public class QbcDemo {
    public static void main(String[] args) {
        Qbctest2();
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
