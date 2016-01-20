package dao;
import models.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Ekaterina on 22.03.2015.
 */
public class ConversationDAO {
    private static Session session;

    public static void setSession(Session session) {
        ConversationDAO.session = session;
    }

    public static Conversation getConversation(Long id) {
        return (Conversation) session.get(Conversation.class, id);
    }

    public static List<Conversation> getConversations() {
        return  session.createQuery("from Conversation").list();
    }

    public static void printConversations(List<Conversation> conversations)
    {
        for (Conversation con : conversations) {
            System.out.println("ID: " + con.getConversId() + "\tВремя: " + con.getTimeconvers() + "\tДлительность.: " + con.getDuration());
        }
    }

    public static void saveConversation(Conversation conversation) {
        session.saveOrUpdate(conversation);
        session.flush();
    }

    /**
     * Обновление переговора
     */
    public static void updateConversation(Integer con_id,Integer cost) {
        Transaction tx = null;
        try
        {
            //редактирование
            tx = session.beginTransaction();

            Conversation con =
                    (Conversation)session.get(Conversation.class,con_id);
            if (con!=null) {
                con.setCostconvers(cost);
                session.update(con);
            }
            else
            {
                System.out.println("\nПереговора с ID : " + con_id +"    в БД нет!" );
            }
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
    }

    /**
     * Удаление переговора
     */
    public static void deleteConversation(Integer con_id) {
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Conversation sub =
                    (Conversation)session.get(Conversation.class,con_id);
            if (sub!=null) {
                session.delete(sub);
            }
            else
            {
                System.out.println("\nПереговора с ID : " + con_id +"    в БД нет!" );
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {

        }
    }


}
