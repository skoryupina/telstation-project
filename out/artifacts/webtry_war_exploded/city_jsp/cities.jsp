<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 15.03.2015
  Time: 5:59
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Города</title>
    <style>
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
                <h2 align="center">Города</h2>
            </td>
        </tr>
        <tr>
            <td>
                <table border="0" class="info">
                    <tr>
                        <td>
                            <form id="insert" action="city.insert" method="get">
                                <button type="submit" name="addCity" class="add"></button>
                            </form>
                        </td>
                        <td>
                            <form id="update"  name="update" action="city.update" method="get">
                                <input type="hidden" name="paramUpd" value="">
                                <button type="submit" name="updCity" onclick="checkUpd()" class="edit"></button>
                            </form>
                        </td>

                        <td>
                            <form id="delete" name="delete" action="city.view" method="get">
                                <input type="hidden" name="paramDel" id="paramDel" value="">
                                <button type="submit" name="delCity" onclick="checkDel()" class="del"></button>
                            </form>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                <!-- Выполнение запроса 1-->
                <form id="search" action="city.view" method="get">
                    <table>
                        <tr>
                            <td colspan="2">Найти город:</td>
                        </tr>
                        <tr>
                            <td><input type="text" id="nameForSearch" required placeholder="Название" name="nameForSearch" size="15"/></td>
                            <td>
                                <input type="hidden" name="paramSearch" id="paramSearch" value="">
                                <button type="submit" name="searchCity" onclick="search()" class="srch">Поиск</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
            <!-- Выполнение запроса 2-->
            <td>
                <form id="searchTarif" action="city.view" method="get">
                    <table width="300">
                        <tr>
                            <td>Найти города c тарифом:</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" id="leftTarif" required placeholder="От" pattern="^[0-9]{1,3}$" name="leftTarif" size="7"/>
                                <input type="text" id="rightTarif" required placeholder="До" pattern="^[0-9]{1,3}$" name="rightTarif" size="7"/>
                            </td>
                            <td>
                                <input type="hidden" name="paramTarif" id="paramTafif " value="">
                                <button type="submit" name="searchCityByTarif" onclick="searchTarif()" class="srch">Поиск</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <!-- Таблички с данными-->
                <form name="table" >

                    <table id="cities" cellpadding="2" cellspacing="2" align="center">
                        <tr>
                            <th class="found"></th>
                            <th class="found">Название</th>
                            <th class="found">Тариф за минуту</th>
                        </tr>
                        <%
                            List cities = (List) request.getAttribute("cities");
                            if (cities != null) {
                                Iterator it = cities.iterator();
                                while (it.hasNext()) {
                        %>
                        <tr>
                            <td class="found"><input type="checkbox" name="cb" id=<%=it.next()%>></td>
                            <td class="found"><%=it.next()%></td>
                            <td class="found"><%=it.next()%></td>
                        </tr>
                        <%
                                }
                                request.setAttribute("cities",null);
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
                        List foundCities = (List) request.getAttribute("foundcities");
                        if (foundCities != null) {
                            Iterator it = foundCities.iterator();
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
            <td >

                <table class="info">
                    <tr>
                        <td><b><p align="center">Результаты поиска:</p> </b></td>
                    </tr>
                    <%
                        foundCities = (List) request.getAttribute("foundbytarif");
                        if (foundCities != null) {
                            Iterator it = foundCities.iterator();
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
