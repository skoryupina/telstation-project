<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 31.03.2015
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" %>
<%@page import="dao.*" %>
<%@page import="models.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Адреса</title>
  <style>
    .main
    {
      margin: auto;
      width: 500px;
    }
    .info {
      /* Ширина таблицы */
      border: 1px; /* Рамка вокруг таблицы */
      margin: auto; /* Выравниваем таблицу по центру окна  */
    }
    td {
      text-align: center; /* Выравниваем текст по центру ячейки */
      vertical-align: top;
    }
  </style>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script src="js/main.js" type="text/javascript"></script>
  <script src="js/addr.js" type="text/javascript"></script>
</head>
<body>
<%
  response.setContentType("text/html; charset=UTF-8");
  response.getWriter();
  request.setCharacterEncoding("UTF-8");
%>
<a href="index.jsp">На главную</a>
<div align="center">
  <table class="cont">
    <tr>
      <td colspan="3">
        <img width = "500" src="images/head.jpg">
      </td>
    </tr>
    <tr>
      <td colspan="3">
        <h2 align="center">Адреса</h2>
      </td>
    </tr>
    <tr>
      <td>
        <table border="0" class="info">
          <tr>
            <td>
              <form id="insert" action="address.insert" method="get">
                <button type="submit" name="addAddr" class="add"></button>
              </form>
            </td>
            <td>
              <form id="update"  name="update" action="address.update" method="get">
                <input type="hidden" name="paramUpd" value="">
                <button type="submit" name="updAddr" onclick="checkUpd()" class="edit"></button>
              </form>
            </td>
            <td>
              <form id="delete" name="delete" action="address.view" method="get">
                <input type="hidden" name="paramDel" id="paramDel" value="">
                <button type="submit" name="delAddress" onclick="checkDel()" class="del"></button>
              </form>
            </td>
          </tr>
        </table>
      </td>
      <td>
        <!-- Выполнение запроса 1-->
        <form id="search" action="address.view" method="get">
          <table>
            <tr>
              <td colspan="2">Найти улицу:</td>
            </tr>
            <tr>
              <td><input type="text" id="nameForSearch" required placeholder="Название" name="nameForSearch" size="30"/></td>
              <td>
                <input type="hidden" name="paramSearch" id="paramSearch" value="">
                <button type="submit" name="searchAddr" onclick="searchStreet()" class="srch">Поиск</button>
              </td>
            </tr>
          </table>
        </form>
      </td>
      <!-- Выполнение запроса 2-->
      <td>
        <form id="searchFlat" action="address.view" method="get">
          <table width="300">
            <tr>
              <td colspan="2">Найти улицы по номеру квартиры:</td>
            </tr>
            <tr>
              <td>
                <input type="text" id="num" required placeholder="Номер" pattern="^[0-9]{1,3}$" name="num" size="12"/>
              </td>
              <td>
                <input type="hidden" name="paramNum" id="paramNum " value="">
                <button type="submit" name="searchStreetByFlat" onclick="searchByFlat()" class="srch">Поиск</button>
              </td>
            </tr>
          </table>
        </form>
      </td>
    </tr>
    <tr>
      <td>
        <form name="table" >

          <table id="addresses" border = 0 cellpadding="2" cellspacing="2" align="center">
            <tr>
              <th class="found"></th>
              <th class="found">Улица</th>
              <th class="found">Дом</th>
              <th class="found">Квартира</th>
            </tr>
            <%
              List addr = (List) request.getAttribute("addresses");
              if ( addr != null) {
                Iterator it =  addr.iterator();
                while (it.hasNext()) {
            %>
            <tr>
              <td class="found"><input type="checkbox" name="cb" id=<%=it.next()%>></td>
              <td class="found"><%=it.next()%></td>
              <td class="found"><%=it.next()%></td>
              <td class="found"><%=it.next()%></td>
            </tr>

            <%
                }
                request.setAttribute("addresses",null);
              }
            %>
          </table>
        </form>
      </td>
      <td>
        <!-- Выполнение запросов-->

        <table class="info">
          <tr>
            <td><b><p align="center">Результаты поиска:</p></b></td>
          </tr>
          <%
            List foundAddr = (List) request.getAttribute("foundaddresses");
            if (foundAddr  != null) {
              Iterator it = foundAddr .iterator();
              while (it.hasNext()) {
          %>
          <tr>
            <td class="found"><%=it.next()%></td>
          </tr>
          <%
              }

            }
          %>
        </table>
      </td>
      <td>
        <table class="info">
          <tr>
            <td><b><p align="center">Результаты поиска:</p></b></td>
          </tr>
          <%
            foundAddr = (List) request.getAttribute("foundbyflat");
            if (foundAddr != null) {
              Iterator it = foundAddr.iterator();
              while (it.hasNext()) {
          %>
          <tr>
            <td class="found"><%=it.next()%></td>
          </tr>
          <%
              }
            }
          %>
        </table>
      </td>
    </tr>
  </table>
</div>
</body>
</html>

