package myservlets.addresservlets;

import dao.CityDAO;
import dao.AddressDAO;
import models.City;

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
public class AddressInsert extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<City> city = CityDAO.getCities();
        List setCity= new LinkedList();
        for (City c: city){
            setCity.add(c.getCityId());
            setCity.add(c.getName());
        }
        request.setAttribute("cities",setCity);

        RequestDispatcher view = request.getRequestDispatcher("addr_jsp/addressInsert.jsp");
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String street = request.getParameter("street");
            String flat = request.getParameter("flat");
            String house = request.getParameter("house");

            Integer city = Integer.parseInt(request.getParameter("paramIns"));
            AddressDAO.insertAddress(street, house, flat, city);
            RequestDispatcher view = request.getRequestDispatcher("addr_jsp/addressInsert.jsp");
            doGet(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

