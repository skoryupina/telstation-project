<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 31.03.2015
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Новый абонент</title>
  <style>
    .info {
      width: 450px; /* Ширина формы */
      margin: auto; /* Выравниваем таблицу по центру окна  */
    }
  </style>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script src="js/subIns.js" type="text/javascript"></script>
</head>
<body>
<%
  response.setContentType("text/html; charset=UTF-8");
  response.getWriter();
  request.setCharacterEncoding("UTF-8");
%>
<a href="sub.view">Вернуться таблице</a>
<div align="center">
  <table class="cont">
    <tr>
      <td>
        <img width = "500" src="images/head.jpg">
      </td>
    </tr>
    <tr>
      <td>
        <h2 align="center">Новый абонент</h2>
        <form action="sub.insert" method="POST" class="info" id="insert" name="insert" >
          <table border="0">
            <tr>
              <td>Имя: </td>
              <td><input type="text" id="name" required placeholder="Введите имя" name="name" size="50"/></td>
            </tr>
            <tr>
              <td>Телефон: </td>
              <td><input type="text" id="tel" required placeholder="Введите номер телефона" name="tel" size="50"/></td>
            </tr>
            <td>Адрес: </td>


            <td>
              <form name="table" >
                <table id="address" border = 0 cellpadding="2" cellspacing="2" align="center">
                  <tr>
                    <th></th>
                    <th>Улица</th>
                    <th>Дом</th>
                    <th>Квартира</th>
                  </tr>
                  <%
                    List addr = (List) request.getAttribute("addr");
                    if (addr != null) {
                      Iterator it = addr.iterator();
                      while (it.hasNext()) {
                  %>
                  <tr>
                    <td><input type="radio" name="addr" id=<%=it.next()%>></td>
                    <td><%=it.next()%></td>
                    <td><%=it.next()%></td>
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
          </table>
          <table border="0">
            <tr>
              <td>
                <input type="reset" class="srch" value="Очистить" name="clear"/>
              </td>
              <td>
                <input type="hidden"   name="paramIns" id="paramIns" value="">
                <input type="submit"  class="srch"  value="Сохранить" onclick="checkIns()"/>
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
