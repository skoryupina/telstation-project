<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 29.03.2015
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
  <meta http-equiv="CONTENT-TYPE" content="text/html; charset=UTF-8">
  <title>Приветствуем отчаянных программистов</title>
  <style>
    body
    {
      background-color: #042134;
    }
  </style>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%@ page import="models.*"%>
  <%
    response.setContentType("text/html; charset=UTF-8");
    response.getWriter ();
    request.setCharacterEncoding ("UTF-8");
    %>
<table border="0" class="cont">
  <tbody>
  <tr>
    <td>
      <img width = "500" src="images/head.jpg">
    </td>
  </tr>
  <tr>
    <td>
      <form method="post" action="city.view">
        <p align="center">
        <button type="submit" name="v_city" class="city"></button>
      </form>
    </td>
  </tr>
  <tr>
    <td>
    <form method="post" action="address.view">
      <p align="center">
      <button type="submit" name="v_address" class="addr"></button>
      </p>
    </form>
    </td>
  </tr>
  <tr>
    <td>
      <form method="post" action="sub.view">
        <p align="center">
        <button type="submit" name="v_sub" class="sub"></button>
        </p>
      </form>
    </td>
  </tr>
  </tbody>
</table>
</body>
</html>