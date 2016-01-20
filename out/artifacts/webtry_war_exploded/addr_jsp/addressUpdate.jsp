<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 31.03.2015
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
  <meta http-equiv="CONTENT-TYPE" content="text/html; charset=UTF-8">
  <title>Редактирование адреса</title>
  <style>
    .info {
      width: 450px; /* Ширина формы */
      margin: auto; /* Выравниваем таблицу по центру окна  */
    }
  </style>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%response.setContentType("text/html; charset=UTF-8");
  response.getWriter ();
  request.setCharacterEncoding ("UTF-8");
%>
<%
  List address = (List)request.getAttribute("address");
  if (address != null) {
    Iterator it = address.iterator();
%>
<%String id=it.next().toString();%>
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
        <h2 align="center">Редактирование адреса</h2>
        <%String prevnstreet=it.next().toString();
          String prevhouse=it.next().toString();
          String prevflat=it.next().toString();
          session.setAttribute("id", id);
          session.setAttribute("prevnstreet", prevnstreet);
          session.setAttribute("prevhouse", prevhouse);
          session.setAttribute("prevflat", prevflat);
        %>
        <form action="address.update" method="POST" class="info">
          <table border="0">
            <tr>
              <td>Улица:</td>
              <td><input type="text" id="street" required placeholder="Введите улицу" name="street" size="50" value="<%=prevnstreet%>"/></td>
            </tr>
            <tr>
              <td>Дом:</td>
              <td><input type="text" id="house" required placeholder="Введите дом" pattern="^[0-9]{1,3}$" name="house" size="50" value="<%=prevhouse%>"/></td>
            </tr>
            <tr>
              <td>Квартира:</td>
              <td><input type="text" id="flat" required placeholder="Введите квартиру" pattern="^[0-9]{1,3}$" name="flat" size="50" value="<%=prevflat%>"/></td>
            </tr>
          </table>
          <table border="0">
            <tr>
              <td>
                <input type="reset" class="srch" value="Очистить" name="clear"/>
              </td>
              <td>
                <input type="submit" class="srch" value="Сохранить" name="submit"/>
              </td>
            </tr>
          </table>
        </form>
        <%}%>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
