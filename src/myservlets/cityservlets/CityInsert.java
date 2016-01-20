package myservlets.cityservlets;
import dao.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ekaterina on 29.03.2015.
 */
public class CityInsert extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("city_jsp/cityInsert.jsp");
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        Integer tarif = new Integer(request.getParameter("tarif"));
        CityDAO.insertCity(name,tarif);
        RequestDispatcher view = request.getRequestDispatcher("city_jsp/cityInsert.jsp");
        view.forward(request, response);
    }
}
