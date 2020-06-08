<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Articulo"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
 List<Articulo> lista =(List<Articulo>) request.getAttribute("lista");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>panel  de blog de administracion</h1>
        <p>Usuario : ${sessionScope.usuario}</p>
        <p>Bienvenido al panel de blog del administrador</p>
        <br>
        <a href="LoginControlador?action=logout">salir [x]</a>
    
        <h1>listado de articulos </h1>
        <p>
            <a href="MainController?op=nuevo"> Nuevo articulo</a>
        </p>
        <table border="1"> 
            <tr>
                <th>Id</th>
                <th>Fecha</th>
                <th>Titulo</th>
                <th>contenido</th>
                <th>autor</th>
                <th></th>
                <th></th>
            </tr> 
            <c:forEach var="item" items="${lista}"> 
            <tr>
                <th>${item.id}</th>
                <th>${item.fecha}</th>
                <th>${item.titulo}</th>
                <th>${item.contenido}</th>
                <th>${item.autor}</th>
                <th><a href="MainController?op=editar&id=${item.id}">Editar</a></th>
                <th><a href="MainController?op=eliminar&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar ??'))">Eliminar</a></th>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
