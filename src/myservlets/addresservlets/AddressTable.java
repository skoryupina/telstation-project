package myservlets.addresservlets;

import dao.AddressDAO;
import models.Address;

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
public class AddressTable extends HttpServlet {
    private ServletConfig config;

    public void init(ServletConfig config)
            throws ServletException {
        this.config = config;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<Address> addresses= AddressDAO.getAddresses();
        List setAddresses = new LinkedList();
        for (Address a: addresses){
            setAddresses.add(a.getAddressId());
            setAddresses.add(a.getStreet());
            setAddresses.add(a.getHouse());
            setAddresses.add(a.getFlat());
        }
        request.setAttribute("addresses",setAddresses);
        RequestDispatcher view = request.getRequestDispatcher("addr_jsp/addresses.jsp");
        view.forward(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("delAddress")!=null) {
            String addrID = request.getParameter("paramDel");
            if ((addrID != null) && (!addrID.equals(""))) {
                /*выделяем ID для удаления*/
                String[] masID = addrID.split("&");
                List<Integer> addrForDel = new LinkedList<Integer>();
                for (int i = 0; i < masID.length; i++) {
                    addrForDel.add(Integer.parseInt(masID[i]));
                }
                /*Вызываем метод удаления*/
                AddressDAO.deleteAddresses(addrForDel);
            }
        }
        else if (request.getParameter("searchAddr")!=null)
        {
            String addr = request.getParameter("paramSearch");
            byte ptext[] = addr.getBytes("ISO-8859-1");
            addr = new String(ptext, "UTF-8");
            List<String> addrs = AddressDAO.findStreets(addr);
            if (!addrs.iterator().hasNext())
            {
                addrs.add("Ничего не найдено");
            }
            request.setAttribute("foundaddresses", addrs);
        }
        else if (request.getParameter("searchStreetByFlat")!=null)
        {
            String flat = request.getParameter("paramNum");
            if ((flat != null) && (!flat.equals(""))) {
                /*выделяем границы*/
                byte ptext[] = flat.getBytes("ISO-8859-1");
                flat = new String(ptext, "UTF-8");
                List<String> streets = AddressDAO.findFlats(flat);
                if (!streets.iterator().hasNext())
                {
                    streets.add("Ничего не найдено");
                }
                request.setAttribute("foundbyflat", streets);
            }
        }
        doPost(request,response);
    }

}
