
import dao.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import models.*;
/**
 * Created by Ekaterina on 22.03.2015.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
/*        CityDAO.setSession(session);

        //----------------- демо с таблицей городов
*/
        //получение списка городов
        List<City> cities = CityDAO.getCities();
        CityDAO.printCities(cities);
/*
        //вывод городов с подстрокой в названии
        *//*cities = CityDAO.findCitiesByStreet("Са");
        CityDAO.printCities(cities);*//*

        //обновление названия города по id
        CityDAO.updateCity(9,"Катюфон");
        cities = CityDAO.getCities();
        CityDAO.printCities(cities);

        //удаление города
        CityDAO.deleteCity(10);
        cities = CityDAO.getCities();
        CityDAO.printCities(cities);*/

        //----------------- демо с таблицей адресов

        /*AddressDAO.setSession(session);
        //получение списка городов
        List<Address> addresses = AddressDAO.getAddresses();
        AddressDAO.printAddresses(addresses);

        //обновление названия улицы по id
        AddressDAO.updateAddress(14, "Андрейка");
        addresses = AddressDAO.getAddresses();
        AddressDAO.printAddresses(addresses);

        //удаление адреса
        AddressDAO.deleteAddress(14);
        addresses = AddressDAO.getAddresses();
        AddressDAO.printAddresses(addresses);*/

        //----------------- демо с таблицей абонентов
        /*SubscriberDAO.setSession(session);
        //получение списка городов
        List<Subscriber> subscribers = SubscriberDAO.getSubscribers();
        SubscriberDAO.printSubscribers(subscribers);

        //обновление названия улицы по id
        SubscriberDAO.updateSubscriber(6, "Зимбаба");
        subscribers = SubscriberDAO.getSubscribers();
        SubscriberDAO.printSubscribers(subscribers);

        //удаление адреса
        SubscriberDAO.deleteSubscriber(6);
        subscribers = SubscriberDAO.getSubscribers();
        SubscriberDAO.printSubscribers(subscribers);*/

        //----------------- демо с таблицей переговоров
       /* ConversationDAO.setSession(session);
        //получение списка городов
        List<Conversation> convs = ConversationDAO.getConversations();
        ConversationDAO.printConversations(convs);

        //обновление названия улицы по id
        ConversationDAO.updateConversation(13,345);
        convs = ConversationDAO.getConversations();
        ConversationDAO.printConversations(convs);

        //удаление адреса
        ConversationDAO.deleteConversation(13);
        convs = ConversationDAO.getConversations();
        ConversationDAO.printConversations(convs);*/
        try {

            session.beginTransaction();

            City city1 = new City();
            city1.setName("Изумрудный город");
            city1.setTarif(67);

            List<Address> addresses1 = new LinkedList<Address>();

            Address addr1 = new Address();
            addr1.setCity(city1);
            addr1.setStreet("Дынная");
            addr1.setHouse("56");
            addr1.setFlat("9");

            Address addr2 = new Address();
            addr2.setCity(city1);
            addr2.setStreet("Клубничная");
            addr2.setHouse("7");
            addr2.setFlat("456");

            Address addr3 = new Address();
            addr3.setCity(city1);
            addr3.setStreet("Молочная");
            addr3.setHouse("23");
            addr3.setFlat("45");

            addresses1.add(addr1);
            addresses1.add(addr2);
            addresses1.add(addr3);

            city1.setAddresses(addresses1);

            List<Subscriber> subs = new LinkedList<Subscriber>();
            Subscriber sub1 = new Subscriber();
            sub1.setName("Любаня");
            sub1.setAddress(addr1);
            sub1.setTel(4563);
            subs.add(sub1);
            addr1.setSubscribers(subs);

            List<Subscriber> subs2 = new LinkedList<Subscriber>();
            Subscriber sub2 = new Subscriber();
            sub2.setName("Иванушка");
            sub2.setAddress(addr2);
            sub2.setTel(4446);
            subs2.add(sub2);
            addr2.setSubscribers(subs2);

            List<Subscriber> subs3 = new LinkedList<Subscriber>();
            Subscriber sub3 = new Subscriber();
            sub3.setName("Аленушка");
            sub3.setAddress(addr3);
            sub3.setTel(4435);
            subs3.add(sub3);
            addr3.setSubscribers(subs3);

            List<Conversation> cons1 = new LinkedList<Conversation>();
            Conversation con1 = new Conversation();
            con1.setSubscriber(sub1);
            con1.setTimeconvers("12:00");
            con1.setDuration(34);
            con1.setDateconvers("13.03.14");
            con1.setCostconvers(34);
            cons1.add(con1);
            sub1.setConversations(cons1);

            List<Conversation> cons2 = new LinkedList<Conversation>();
            Conversation con2 = new Conversation();
            con2.setSubscriber(sub2);
            con2.setTimeconvers("14:00");
            con2.setDuration(355);
            con2.setDateconvers("16.03.14");
            con2.setCostconvers(389);
            cons2.add(con2);
            sub2.setConversations(cons2);

            List<Conversation> cons3 = new LinkedList<Conversation>();
            Conversation con3 = new Conversation();
            con3.setSubscriber(sub2);
            con3.setTimeconvers("00:00");
            con3.setDuration(32);
            con3.setDateconvers("19.03.14");
            con3.setCostconvers(366);
            cons3.add(con3);
            sub3.setConversations(cons3);

           /* session.save(city1);
            session.save(addr1);
            session.save(addr2);
            session.save(addr3);*/
            session.save(city1);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
