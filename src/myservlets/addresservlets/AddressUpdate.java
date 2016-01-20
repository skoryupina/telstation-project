package myservlets.addresservlets;

import dao.AddressDAO;
import models.Address;

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
public class AddressUpdate  extends HttpServlet{
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String id = request.getParameter("paramUpd");
            if((id!=null)&&(!id.equals(""))) {
                Address addr = AddressDAO.getAddress(Integer.parseInt(id));
                List updAddr = new LinkedList();
                updAddr.add(addr.getAddressId());
                updAddr.add(addr.getStreet());
                updAddr.add(addr.getHouse());
                updAddr.add(addr.getFlat());
                request.setAttribute("address", updAddr);
            }
            RequestDispatcher view = request.getRequestDispatcher("addr_jsp/addressUpdate.jsp");
            view.forward(request, response);
        }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("submit")!=null) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter();
            /*получаем новые значения*/
            request.setCharacterEncoding("UTF-8");
            String street = request.getParameter("street");
            String house = request.getParameter("house");
            String flat = request.getParameter("flat");

            HttpSession session = request.getSession();
            Integer id = Integer.parseInt((String) session.getAttribute("id"));
            /*обновляем */
            AddressDAO.updateAddress(id, street, flat, house);
            response.sendRedirect("address.view");
        }
    }
}
