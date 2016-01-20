package dao;
import models.*;

import myservlets.HibernateConfig;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Ekaterina on 22.03.2015.
 */
public class SubscriberDAO {
    private static Session session;

    public static void setSession() {
        SubscriberDAO.session = HibernateConfig.getSession();
    }

    public static Subscriber getSubscriber(Integer id) {
        try {
            setSession();
            return (Subscriber) session.get(Subscriber.class, id);
        }
        finally {
            session.close();
        }
    }

    public static List<Subscriber> getSubscribers() {
        try {
            setSession();
            return  session.createQuery("from Subscriber").list();
        }
        finally {
            session.close();
        }
    }

    public static void printSubscribers(List<Subscriber> subscribers)
    {
        for (Subscriber sub : subscribers) {
            System.out.println("ID: " + sub.getAccountNumber() + "\tИмя: " + sub.getName() + "\tТел.: " + sub.getTel());
        }
    }

    public static void saveSubscriber(Subscriber subscriber) {
        session.saveOrUpdate(subscriber);
        session.flush();
    }

    public static int insertSub(String name,Integer tel, Integer addr) {
        setSession();
        int id=-1;
        Transaction t=null;
        try {
            t = session.beginTransaction();
            Address addr_o =
                    (Address)session.get(Address.class,addr);
            if (addr_o!=null) {
                Subscriber sub = new Subscriber();
                sub.setName(name);
                sub.setTel(tel);
                /*Добавляем нового абонента*/
                List<Subscriber> subList = addr_o.getSubscribers();
                subList.add(sub);
                addr_o.setSubscribers(subList);

                session.update(addr_o);
                sub.setAddress(addr_o);
                session.save(sub);
                id=sub.getAccountNumber();
            }
            else
            {
                System.out.println("\nАдреса с ID : " + addr +"    в БД нет!" );
            }
            t.commit();
        } catch (HibernateException ex) {
            if(t!=null)
                t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
            return id;
        }
    }

    /**
     * Обновление абонента
     */
    public static void updateSubscriber(Integer sub_id,String name,Integer tel) {
        Transaction tx = null;
        try
        {
            //редактирование
            setSession();
            tx = session.beginTransaction();

            Subscriber sub =
                    (Subscriber)session.get(Subscriber.class,sub_id);
            if (sub!=null) {
                sub.setName(name);
                sub.setTel(tel);
                session.update(sub);
            }
            else
            {
                System.out.println("\nАбонента с ID : " + sub_id +"    в БД нет!" );
            }
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void deleteSubscribers(List<Integer> subID){
        Transaction tx = null;
        try{
            setSession();
            tx = session.beginTransaction();
            Iterator it = subID.iterator();
            while (it.hasNext())
            {
                Integer id = (Integer)it.next();
                Subscriber sub =
                        (Subscriber)session.get(Subscriber.class,id);
                if (sub!=null)
                {
                    session.delete(sub);
                }
                else
                {
                    System.out.println("\nАбонента с ID : " + "    в БД нет!");
                }
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /**
     * Удаление абонента
     */
    public static void deleteSubscriber(Integer sub_id) {
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Subscriber sub =
                    (Subscriber)session.get(Subscriber.class,sub_id);
            if (sub!=null) {
                session.delete(sub);
            }
            else
            {
                System.out.println("\nАбонента с ID : " + sub_id +"    в БД нет!" );
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    //Примеры запросов

    /**
     * Объединение связанных таблиц через "точечный" синтаксис, привязка параметров
     * Поиск по всем адресам, где имеется указанный абонент
     */
    public static List<String> findSubs(String name) {
        try {
            setSession();
            Query query = session.createQuery("select sub.name from Subscriber as sub where sub.name like :name");
            query.setString("name", "%" + name + "%");
            return query.list();
        }
        finally {
            session.close();
        }
    }

    public static List<String> findbByTel(Integer num) {
        try {
            setSession();
            Query query = session.createQuery("select sub.name from Subscriber as sub where sub.tel=:num");
            query.setInteger("num", num);
            return query.list();
        }
        finally {
            session.close();
        }
    }
}
