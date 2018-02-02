<%-- 
    Document   : provinsihal
    Created on : Feb 2, 2018, 3:32:26 PM
    Author     : St0rm
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Halaman Provinsi</title>
    </head>
    <body>
        <h1>Menampilkan Provinsi</h1>
        <p>ID dari Provinsi = ${provinsi.idProv}</p>
        <p>ID Negara dari Provinsi = ${provinsi.id_negara}</p>
        <p>Nama Provinsi Tersebut = ${provinsi.nama_propinsi}</p>
        <table border="1">
            <th>ID Provinsi</th>
            <th>ID Negara</th>
            <th>Nama Provinsi</th>
                <c:forEach items="${listprov}" var="prov">
                <tr>
                    <td>${prov.idProv}</td>
                    <td>${prov.id_negara}</td>
                    <td>${prov.nama_propinsi}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
