package dao;
import models.*;
import myservlets.HibernateConfig;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Ekaterina on 17.03.2015.
 */
public class CityDAO {
    private static Session session;

    public static void setSession() {
        CityDAO.session = HibernateConfig.getSession();
    }

    /**
     * Получение города по его идентификатору
     *
     * @param id идентификатор города
     * @return город
     */
    public static City getCity(Integer id) {
        if (!session.isOpen()) {
            session = HibernateConfig.getSession();
        }
        return (City) session.get(City.class, id);
    }

    /**
     * Получение списка всех городов
     *
     * @return список городов
     */
    public static List<City> getCities() {
        try {
            setSession();
            return session.createQuery("from City").list();
        }
        finally {
            session.close();
        }
    }

    public static void printCities(List<City> cities)
    {
        for (City city : cities) {
            System.out.println("ID: " + city.getCityId() + "\tНазвание: " + city.getName() + "\tТариф: " + city.getTarif());
        }
    }

    /**
     * Сохранение (добавление или обновление)
     * города и всех связанных с ним сущностей
     *
     * @param city
     * @return сохраненный город
     */
    public static void saveCity(City city) {
        try {
            setSession();
            session.saveOrUpdate(city);
            session.flush();
        }
        finally {
            session.close();
        }
    }

    public static int insertCity(String name, Integer tarif) {
        setSession();
        int id=-1;
        Transaction t=null;
        try {
            t = session.beginTransaction();
            City city = new City();
            city.setName(name);
            city.setTarif(tarif);
            session.save(city);
            id=city.getCityId();
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


    //Редактирование города
    public static void updateCity(Integer city_id,String name, Integer tarif)
    {
        Transaction tx = null;
        try {
            setSession();
            //редактирование
            tx = session.beginTransaction();
            City city =
                    (City)session.get(City.class,city_id);
            if (city!=null) {
                city.setName(name);
                city.setTarif(tarif);
                session.update(city);
            }
            else
            {
                System.out.println("\nГорода с ID : " + city_id +"    в БД нет!" );
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

    //Удаление списка городов
    public static void deleteCities(List<Integer> cityID){
        Transaction tx = null;
        try{
            setSession();
            tx = session.beginTransaction();
            Iterator it = cityID.iterator();
            while (it.hasNext())
            {
                /*Удаляем город и все связанные с ним сущности*/
                Integer id = (Integer)it.next();
                City city =
                        (City)session.get(City.class,id);
                if (city!=null)
                {
                    session.delete(city);
                }
                else
                {
                    System.out.println("\nГорода с ID : " + id+ "    в БД нет!");
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

    //Удаление города
    public static void deleteCity(Integer city_id){
        Transaction tx = null;
        try{
            if (!session.isOpen()) {
                session = HibernateConfig.getSession();
            }
            tx = session.beginTransaction();
            City city =
                    (City)session.get(City.class,city_id);
            if (city!=null)
            {
                session.delete(city);
            }
            else
            {
                System.out.println("\nГорода с ID : " + city_id+ "    в БД нет!");
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
     * Поиск по всем городам у которых название включает подстроку
     */
    public static List<String> findCitiesByStreet(String name) {
        try {
            setSession();
            Query query = session.createQuery("select city.name from City as city where city.name like :name");
            query.setString("name", "%" + name + "%");
            return query.list();
        }
        finally {
            session.close();
        }
    }

    /**
     * Поиск по всем городам у которых тариф в заданном интервале
     */
    public static List<String> findCitiesByTarif(int left,int right) {
        try {
            setSession();
            Query query = session.createQuery("select city.name from City as city where city.tarif > :left AND city.tarif< :right");
            query.setInteger("left", left);
            query.setInteger("right", right);
            return query.list();
        }
        finally {
            session.close();
        }
    }

    /**
     * Группировка, конструкция select new map
     * Возвращает все города и количество элементов в них
     * в виде списка ассоциативных массивов
     */
    public static List<Map<String, Object>> getStatistics() {
        try {
            if (!session.isOpen()) {
                session = HibernateConfig.getSession();
            }
            Query query = session.createQuery(
                    "select new map(city.name as Name, (select count(*) from city.addresses) as Number) from City as city");
            return query.list();
        }
        finally {
            session.close();
        }
    }


}