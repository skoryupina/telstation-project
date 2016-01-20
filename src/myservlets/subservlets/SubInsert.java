package myservlets.subservlets;

import dao.AddressDAO;
import dao.CityDAO;
import dao.SubscriberDAO;
import models.Address;
import models.Subscriber;

import javax.servlet.RequestDispatcher;
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
public class SubInsert extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Address> addr = AddressDAO.getAddresses();
        List setAddr= new LinkedList();
        for (Address c: addr){
            setAddr.add(c.getAddressId());
            setAddr.add(c.getStreet());
            setAddr.add(c.getHouse());
            setAddr.add(c.getFlat());
        }
        request.setAttribute("addr",setAddr);

        RequestDispatcher view = request.getRequestDispatcher("sub_jsp/subInsert.jsp");
        view.forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        Integer tel = Integer.parseInt(request.getParameter("tel"));
        Integer addr = Integer.parseInt(request.getParameter("paramIns"));
        SubscriberDAO.insertSub(name, tel, addr);
        RequestDispatcher view = request.getRequestDispatcher("sub_jsp/subInsert.jsp");

        doGet(request, response);
    }
}
