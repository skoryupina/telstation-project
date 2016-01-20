package myservlets.cityservlets;
import dao.CityDAO;
import models.*;
import org.hibernate.Session;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Created by Ekaterina on 29.03.2015.
 */
public class CityTable extends HttpServlet  {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<City> cities = CityDAO.getCities();
        List setCities = new LinkedList();
        for (City c: cities){
            setCities.add(c.getCityId());
            setCities.add(c.getName());
            setCities.add(c.getTarif());
        }
        request.setAttribute("cities",setCities);
        RequestDispatcher view = request.getRequestDispatcher("city_jsp/cities.jsp");
        view.forward(request,response);
}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("delCity")!=null) {
            String citiesID = request.getParameter("paramDel");
            if ((citiesID != null) && (!citiesID.equals(""))) {
                /*выделяем ID для удаления*/
                String[] masID = citiesID.split("&");
                List<Integer> citiesForDel = new LinkedList<Integer>();
                for (int i = 0; i < masID.length; i++) {
                    citiesForDel.add(Integer.parseInt(masID[i]));
                }
                /*Вызываем метод удаления*/
                CityDAO.deleteCities(citiesForDel);
            }
        }
        else if (request.getParameter("searchCity")!=null)
        {
            String city = request.getParameter("paramSearch");
            byte ptext[] = city.getBytes("ISO-8859-1");
            city = new String(ptext, "UTF-8");
            List<String> cities = CityDAO.findCitiesByStreet(city);
            if (!cities.iterator().hasNext())
            {
                cities.add("Ничего не найдено");
            }
            request.setAttribute("foundcities", cities);
        }
        else if (request.getParameter("searchCityByTarif")!=null)
        {
            String borders = request.getParameter("paramTarif");
            if ((borders != null) && (!borders.equals(""))) {
                /*выделяем границы*/
                String[] bord = borders.split("&");
                int left = new Integer(bord[0]);
                int right = new Integer(bord[1]);

                List<String> cities = CityDAO.findCitiesByTarif(left,right);
                if (!cities.iterator().hasNext())
                {
                    cities.add("Ничего не найдено");
                }
                request.setAttribute("foundbytarif", cities);
            }
        }
        doPost(request,response);
    }

}
