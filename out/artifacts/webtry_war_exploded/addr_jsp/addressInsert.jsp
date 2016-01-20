<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 31.03.2015
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Новый адрес</title>
  <style >
    .info {
      width: 450px; /* Ширина формы */
      margin: auto; /* Выравниваем таблицу по центру окна  */
    }
  </style>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script src="js/addrIns.js" type="text/javascript"></script>
</head>
<body>
<%
  response.setContentType("text/html; charset=UTF-8");
  response.getWriter();
  request.setCharacterEncoding("UTF-8");
%>
<a href="address.view">Вернуться таблице</a>
<div align="center">
  <table class="cont">
    <tr>
      <td>
        <img width = "500" src="images/head.jpg">
      </td>
    </tr>
    <tr>
      <td>
        <h2 align="center">Новый адрес</h2>
        <form action="address.insert" method="POST" class="info" id="insert" name="insert">
          <table border="0">
            <tr>
              <td>Город:
                <form name="table" >
                  <table id="cities" border = 0 cellpadding="2" cellspacing="2" align="center">
                    <tr>
                      <th></th>
                      <th>Название</th>
                    </tr>
                    <%
                      List cities = (List) request.getAttribute("cities");
                      if (cities != null) {
                        Iterator it = cities.iterator();
                        while (it.hasNext()) {
                    %>
                    <tr>
                      <td><input type="radio" name="city" id=<%=it.next()%>></td>
                      <td><%=it.next()%></td>
                    </tr>
                    <%
                        }
                      }
                    %>
                  </table>
                </form>

              </td>
            </tr>
            <tr>
              <td>Улица: </td>
              <td><input type="text" id="street" required placeholder="Введите улицу" name="street" size="50"/></td>
            </tr>
            <tr>
              <td>Дом:</td>
              <td><input type="text" id="house" required placeholder="Введите дом" name="house" size="50"/></td>
            </tr>
            <tr>
              <td>Квартира:</td>
              <td><input type="text" id="flat" required placeholder="Введите квартиру" name="flat" size="50"/></td>
            </tr>
          </table>
          <table border="0">
            <tr>
              <td>
                <input type="reset" class="srch" value="Очистить" name="clear"/>
              </td>
              <td>
                <input type="hidden"   name="paramIns" id="paramIns" value="">
                <input type="submit" class="srch"  value="Сохранить" onclick="checkIns()"/>
              </td>
            </tr>
          </table>
        </form>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
