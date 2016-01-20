<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 31.03.2015
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
  <meta http-equiv="CONTENT-TYPE" content="text/html; charset=UTF-8">
  <title>Редактирование абонента</title>
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
  List sub = (List)request.getAttribute("sub");
  if (sub != null) {
    Iterator it = sub.iterator();
%>
<%String id=it.next().toString();%>
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
        <h2 align="center">Редактирование абонента</h2>
        <%String prevname=it.next().toString();
          String prevtel=it.next().toString();
          session.setAttribute("id", id);
          session.setAttribute("prevname", prevname);
          session.setAttribute("prevtel", prevtel);
        %>
        <form action="sub.update" method="POST" class="info">
          <table border="0">
            <tr>
              <td>Имя:</td>
              <td><input type="text" id="name" required placeholder="Введите имя" name="name" size="50" value="<%=prevname%>"/></td>
            </tr>
            <tr>
              <td>Телефон:</td>
              <td><input type="text" id="tel" required placeholder="Введите телефон" pattern="^[0-9]{1,6}$" name="tel" size="50" value="<%=prevtel%>"/></td>
            </tr>
          </table>
          <table border="0">
            <tr>
              <td>
                <input type="reset" class="srch"  value="Очистить" name="clear"/>
              </td>
              <td>
                <input type="submit" class="srch"  value="Сохранить" name="submit"/>
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