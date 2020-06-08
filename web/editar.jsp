<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Articulo"%>
<%
   Articulo articulo =(Articulo) request.getAttribute("articulo");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${articulo.id == 0}">Nuevo articulo</c:if>
            <c:if test="${articulo.id != 0}">editar articulo</c:if>
        </h1>
         <form action="MainController" method="post">
            <table>
                <input type="hidden" name='id' value="${item.id}">
                <tr>
                    <td>fecha</td>
                    <td><input type='text' name="fecha" value="${item.fecha}"></td>
                </tr>
                <tr>
                    <td>titulo</td>
                    <td><input type='text' name="titulo" value="${item.titulo}"></td>
                </tr>
                <tr>
                    <td>contenido</td>
                    <td><input type='text' name="contenido" value="${item.contenido}"></td>
                </tr>
                <tr>
                    <td>autor</td>
                    <td><input type='text' name="autor" value="${item.autor}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type='submit' value="Enviar"></td>
                </tr>
            </table>
            
        </form>
    </body>
</html>
