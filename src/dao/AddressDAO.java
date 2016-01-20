package dao;

import com.sun.jndi.cosnaming.IiopUrl;
import models.*;
import myservlets.HibernateConfig;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Ekaterina on 17.03.2015.
 */
public class AddressDAO {
    private static Session session;

    public static void setSession() {
        AddressDAO.session = HibernateConfig.getSession();
    }

    /**
     * Получение адреса по его идентификатору
     *
     * @param id идентификатор адреса
     * @return адрес
     */
    public static Address getAddress(Integer id) {
        try {
            setSession();
            return (Address) session.get(Address.class, id);
        }
        finally {
            session.close();
        }
    }

    /**
     * Получение списка всех адресов
     *
     * @return список адресов
     */
    public static List<Address> getAddresses() {
        try {
            setSession();
            return session.createQuery("from Address").list();
        }
        finally {
            session.close();
        }
    }


    public static void printAddresses(List<Address> addresses)
    {
        for (Address addr : addresses) {
            System.out.println("ID: " + addr.getAddressId() + "\tУлица: " + addr.getStreet() + "\tДом: " + addr.getHouse() + "\tКв.: " + addr.getFlat());
        }
    }

    /**
     * Сохранение (добавление или обновление)
     * адреса и всех связанных с ним сущностей
     *
     * @param address
     * @return сохраненный адрес
     */
    public static void saveAвdress(Address address) {
        try {
            setSession();
            session.saveOrUpdate(address);
            session.flush();
        }
        finally {
            session.close();
        }
    }


    public static int insertAddress(String street,String house, String flat, Integer city) {
        setSession();
        int id=-1;
        Transaction t=null;
        try {
            t = session.beginTransaction();
            City city_o =
                    (City)session.get(City.class,city);
            if (city_o!=null) {
                Address addr = new Address();
                addr.setStreet(street);
                addr.setHouse(house);
                addr.setFlat(flat);

                List<Address> adresses = city_o.getAddresses();
                if(adresses!=null){
                    adresses.add(addr);
                    city_o.setAddresses(adresses);
                }
                session.update(city_o);
                addr.setCity(city_o);
                session.save(addr);
                id=addr.getAddressId();
            }
            else
            {
                System.out.println("\nГорода с ID : " + city +"    в БД нет!" );
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
     * Обновление адреса
     */
    public static void updateAddress(Integer address_id,String name,String flat,String house) {
        Transaction tx = null;
        try
        {
            setSession();
            //редактирование
            tx = session.beginTransaction();

            Address address =
                    (Address)session.get(Address.class,address_id);
            if (address!=null) {
                address.setStreet(name);
                address.setHouse(house);
                address.setFlat(flat);
                session.update(address);
            }
            else
            {
                System.out.println("\nАдреса с ID : " + address_id +"    в БД нет!" );
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

    //Удаление списка адресов
    public static void deleteAddresses(List<Integer> addrID){
        Transaction tx = null;
        try{
            setSession();
            tx = session.beginTransaction();
            Iterator it = addrID.iterator();
            while (it.hasNext())
            {
                Integer id = (Integer)it.next();
                Address address =
                        (Address)session.get(Address.class,id);
                if (address!=null)
                {
                    session.delete(address);
                }
                else
                {
                    System.out.println("\nАдреса с ID : " + id+ "    в БД нет!");
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
     * Удаление адреса
     */
    public static void deleteAddress(Integer address_id) {
        Transaction tx = null;
        try{
            setSession();
            tx = session.beginTransaction();
            Address address =
                    (Address)session.get(Address.class,address_id);
            if (address!=null)
            {
                session.delete(address);
            }
            else
            {
                System.out.println("\nАдреса с ID : " + address_id + "    в БД нет!");
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static List<String> findStreets(String street) {
        try {
            setSession();
            Query query = session.createQuery("select addr.street from Address as addr where addr.street like :street");
            query.setString("street", "%" + street + "%");
            return query.list();
        }
        finally {
            session.close();
        }
    }

    public static List<String> findFlats(String num) {
        try {
            setSession();
            Query query = session.createQuery("select addr.street from Address as addr where addr.flat=:flat");
            query.setString("flat", num);
            return query.list();
        }
        finally {
            session.close();
        }
    }



}
