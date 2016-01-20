package myservlets.subservlets;

import dao.AddressDAO;
import dao.SubscriberDAO;
import models.Address;
import models.Subscriber;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ekaterina on 31.03.2015.
 */
public class SubUpdate extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("paramUpd");
        if((id!=null)&&(!id.equals(""))) {
            Subscriber sub = SubscriberDAO.getSubscriber(Integer.parseInt(id));
            List updSub = new LinkedList();
            updSub.add(sub.getAccountNumber());
            updSub.add(sub.getName());
            updSub.add(sub.getTel());
            request.setAttribute("sub", updSub);
        }
        RequestDispatcher view = request.getRequestDispatcher("sub_jsp/subUpdate.jsp");
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("submit")!=null) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter();
            /*получаем новые значения*/
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            Integer tel = Integer.parseInt(request.getParameter("tel"));

            HttpSession session = request.getSession();
            Integer id = Integer.parseInt((String) session.getAttribute("id"));
            /*обновляем */
            SubscriberDAO.updateSubscriber(id,name,tel);
            response.sendRedirect("sub.view");
        }
    }
}
