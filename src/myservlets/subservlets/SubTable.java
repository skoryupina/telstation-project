package myservlets.subservlets;

import dao.SubscriberDAO;
import models.Subscriber;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ekaterina on 31.03.2015.
 */
public class SubTable extends HttpServlet {
    private ServletConfig config;

    public void init(ServletConfig config)
            throws ServletException {
        this.config = config;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<Subscriber> subs= SubscriberDAO.getSubscribers();
        List setSubs = new LinkedList();
        for (Subscriber a: subs){
            setSubs.add(a.getAccountNumber());
            setSubs.add(a.getName());
            setSubs.add(a.getTel());
        }
        request.setAttribute("subs",setSubs);
        RequestDispatcher view = request.getRequestDispatcher("sub_jsp/subs.jsp");
        view.forward(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("delSub")!=null) {
            String subID = request.getParameter("paramDel");
            if ((subID != null) && (!subID.equals(""))) {
                /*выделяем ID для удаления*/
                String[] masID = subID.split("&");
                List<Integer> subForDel = new LinkedList<Integer>();
                for (int i = 0; i < masID.length; i++) {
                    subForDel.add(Integer.parseInt(masID[i]));
                }
                /*Вызываем метод удаления*/
                SubscriberDAO.deleteSubscribers(subForDel);
            }
        }
        else if (request.getParameter("searchSub")!=null)
        {
            //request.setCharacterEncoding("UTF-8");
            String sub = request.getParameter("paramSearch");
            byte ptext[] = sub.getBytes("ISO-8859-1");
            sub = new String(ptext, "UTF-8");
            List<String> subs = SubscriberDAO.findSubs(sub);
            if (!subs.iterator().hasNext())
            {
                subs.add("Ничего не найдено");
            }
            request.setAttribute("foundsubs", subs);
        }
        else if (request.getParameter("searchSubByTel")!=null)
        {
            String num_s = request.getParameter("paramNum");
            if ((num_s != null) && (!num_s.equals(""))) {
                Integer num = Integer.parseInt(num_s);
                List<String> subs = SubscriberDAO.findbByTel(num);
                if (!subs.iterator().hasNext())
                {
                    subs.add("Ничего не найдено");
                }
                request.setAttribute("foundbytel", subs);
            }
        }
        doPost(request,response);
    }
}
