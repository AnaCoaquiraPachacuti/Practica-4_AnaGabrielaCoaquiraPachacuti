<%-- 
    Document   : login
    Created on : 06-jun-2020, 18:51:19
    Author     : jung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="LoginControlador" method="post">
            <label> Usuario </label>
            <input type="text" name="usuario">
            <br>
            <label> Password </label>
            <input type="password" name="password">
            <br>
            <input type="submit" value="Ingresar">
            
        </form>
    </body>
</html>
