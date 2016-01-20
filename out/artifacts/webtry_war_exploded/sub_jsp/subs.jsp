<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 31.03.2015
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Абоненты</title>
  <style>
    .main
    {
      margin: auto;
      width: 500px;
    }
    .info {
      /* Ширина таблицы */
      border: 0px; /* Рамка вокруг таблицы */
      margin: auto; /* Выравниваем таблицу по центру окна  */
    }
    td {
      text-align: center; /* Выравниваем текст по центру ячейки */
      vertical-align: top;
    }
  </style>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script src="js/main.js" type="text/javascript"></script>
  <script>
    function getSub()
    {
      var tb = document.getElementById("nameForSearch");
      if (tb.value.length!=0)
      {
        var form = document.getElementById("search");
        console.log(form);
        form.paramSearch.value=tb.value;
        console.log(form.paramSearch.value);
        form.submit();
      }
      else {
        window.alert("Введите строку для поиска!");
      }
    }
  </script>
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
        <h2 align="center">Абоненты</h2>
      </td>
    </tr>
    <tr>
      <td>
        <table border="0" class="info">
          <tr>
            <td>
              <form id="insert" action="sub.insert" method="get">
                <button type="submit" name="addSub" class="add"></button>
              </form>
            </td>
            <td>
              <form id="update"  name="update" action="sub.update" method="get">
                <input type="hidden" name="paramUpd" value="">
                <button type="submit" name="updSub" onclick="checkUpd()" class="edit"></button>
              </form>
            </td>
            <td>
              <form id="delete" name="delete" action="sub.view" method="get">
                <input type="hidden" name="paramDel" id="paramDel" value="">
                <button type="submit" name="delSub" onclick="checkDel()" class="del"></button>
              </form>
            </td>
          </tr>
        </table>
      </td>
      <td>
        <!-- Выполнение запроса 1-->
        <form id="search" action="sub.view" method="get">
          <table>
            <tr>
              <td colspan="2">Найти абонента:</td>
            </tr>
            <tr>
              <td><input type="text" id="nameForSearch" required placeholder="Имя" name="nameForSearch" size="15"/></td>
              <td>
                <input type="hidden" name="paramSearch" id="paramSearch" value="">
                <button type="submit" name="searchSub" onclick="getSub()" class="srch">Поиск</button>
              </td>
            </tr>
          </table>
        </form>
      </td>
      <!-- Выполнение запроса 2-->
      <td>
        <form id="searchTel" action="sub.view" method="get">
          <table width="300">
            <tr>
              <td colspan="2">Поиск по номеру телефона:</td>
            </tr>
            <tr>
              <td>
                <input type="text" id="num" required placeholder="Номер" pattern="^[0-9]{1,6}$" name="num" size="7"/>
              </td>
              <td>
                <input type="hidden" name="paramNum" id="paramNum " value="">
                <button type="submit" name="searchSubByTel" onclick="searchByTel()" class="srch">Поиск</button>
              </td>
            </tr>
          </table>
        </form>
      </td>
    </tr>
    <tr>
      <!-- Таблички с данными-->
      <td>
        <form name="table" >

          <table id="addresses" border = 0 cellpadding="2" cellspacing="2" align="center">
            <tr>
              <th  class="found"></th>
              <th  class="found">Имя</th>
              <th  class="found">Телефон</th>
            </tr>
            <%
              List subs = (List) request.getAttribute("subs");
              if ( subs != null) {
                Iterator it =  subs.iterator();
                while (it.hasNext()) {
            %>
            <tr>
              <td  class="found"><input type="checkbox" name="cb" id=<%=it.next()%>></td>
              <td  class="found"><%=it.next()%></td>
              <td  class="found"><%=it.next()%></td>
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
        <table>
          <tr>
            <td><b><p align="center">Результаты поиска:</p></b></td>
          </tr>
          <%
            List foundSubs = (List) request.getAttribute("foundsubs");
            if (foundSubs  != null) {
              Iterator it = foundSubs .iterator();
              while (it.hasNext()) {
          %>
          <tr>
            <td><%=it.next()%></td>
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
            foundSubs = (List) request.getAttribute("foundbytel");
            if (foundSubs != null) {
              Iterator it = foundSubs.iterator();
              while (it.hasNext()) {
          %>
          <tr>
            <td><%=it.next()%></td>
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

