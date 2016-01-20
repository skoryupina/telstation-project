<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 29.03.2015
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Новый город</title>
  <style>
    .info {
      width: 450px; /* Ширина формы */
      margin: auto; /* Выравниваем таблицу по центру окна  */
    }
  </style>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%
  response.setContentType("text/html; charset=UTF-8");
  response.getWriter();
  request.setCharacterEncoding("UTF-8");
%>

<a href="city.view">Вернуться таблице</a>
<div align="center">
  <table class="cont">
    <tr>
      <td>
        <img width = "500" src="images/head.jpg">
      </td>
    </tr>
    <tr>
      <td>
        <h2 align="center">Новый город</h2>
        <br/>
        <form action="city.insert" method="POST" class="info">
          <table border="0">
            <tr>
              <td>Название: </td>
              <td><input type="text" id="name" required placeholder="Введите название города" name="name" size="50"/></td>
            </tr>
            <tr>
              <td>Тариф:</td>
              <td><input type="text" id="tarif" required placeholder="Введите число от 1 до 999" pattern="^[0-9]{1,3}$" name="tarif" size="50"/></td>
            </tr>
          </table>
          <table border="0">
            <tr>
              <td>
                <input type="reset" value="Очистить" class="srch" name="clear"/>
              </td>
              <td>
                <input type="submit" value="Сохранить" class="srch" name="submit"/>
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