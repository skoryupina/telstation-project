package myservlets.cityservlets;
import dao.*;
import models.*;
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
 * Created by Ekaterina on 29.03.2015.
 */
public class CityUpdate extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("paramUpd");
        if((id!=null)&&(!id.equals(""))) {
            City city = CityDAO.getCity(Integer.parseInt(id));
            List updCity = new LinkedList();
            updCity.add(city.getCityId());
            updCity.add(city.getName());
            updCity.add(city.getTarif());
            request.setAttribute("city", updCity);
        }
        RequestDispatcher view = request.getRequestDispatcher("city_jsp/cityUpdate.jsp");
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("submit")!=null) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter();
            /*получаем новые значения*/
            String name = request.getParameter("name");
            Integer tarif = Integer.parseInt(request.getParameter("tarif"));

            HttpSession session = request.getSession();
            Integer id = Integer.parseInt((String) session.getAttribute("id"));
            /*обновляем */
            CityDAO.updateCity(id,name,tarif);
            response.sendRedirect("city.view");
        }
    }
}
