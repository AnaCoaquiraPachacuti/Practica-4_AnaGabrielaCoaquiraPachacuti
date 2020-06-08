
package com.emergentes.controlador;

import com.emergentes.modelo.Articulo;
import com.emergentes.utiles.conexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        String op;
        op =(request.getParameter("op") != null) ? request.getParameter("op") : "list";
        
        ArrayList<Articulo> lista = new ArrayList<Articulo>();
        
          conexionDB canal = new conexionDB();
          Connection conn = canal.conectar();
          
          
          PreparedStatement ps;
          ResultSet rs;
          
          if(op.equals("list")){
              try{
                  String sql ="select * from articulos";
                  ps = conn.prepareStatement(sql);
                  rs = ps.executeQuery();
                  
                  while(rs.next()){
                    Articulo art =new Articulo();
                    art.setId(rs.getInt("id"));
                    art.setFecha(rs.getString("fecha"));
                    art.setTitulo(rs.getString("titulo"));
                    art.setContenido(rs.getString("contenido"));
                    art.setAutor(rs.getString("autor"));
                    
                    lista.add(art);
                  }
                  request.setAttribute("lista", lista);
                  request.getRequestDispatcher("panel_blog.jsp").forward(request, response);
              }
              catch(SQLException ex){
                  System.out.println(" Error en SQL"+ ex.getMessage());
              }
              finally{
                canal.desconectar();
              }
          }
          if(op.equals("nuevo")){
             Articulo a= new Articulo();
             request.setAttribute("articulo", a);
             request.getRequestDispatcher("editar.jsp").forward(request, response);
          }
          if(op.equals("editar")){
            try {
                int id = Integer.parseInt(request.getParameter(("id")));
                String sql = "select * from articulos where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                Articulo ar= new Articulo();
                while(rs.next()){
                    ar.setId(rs.getInt("id"));
                    ar.setFecha(rs.getString("fecha"));
                    ar.setTitulo(rs.getString("titulo"));
                    ar.setContenido(rs.getString("contenido"));
                    ar.setAutor(rs.getString("autor"));
                }
                request.setAttribute("libro", ar);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                
            } catch (SQLException ex) {
                System.out.println("Error en sql"+ex.getMessage());
            }
          }
          if(op.equals("eliminar")){
            try {
                int id = Integer.parseInt(request.getParameter(("id")));
                
                String sql = "delete from articulos where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error de SQL " + ex.getMessage());
            }
            finally{
             canal.desconectar();
            }
            response.sendRedirect("MainController");
          }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       String fecha = request.getParameter("fecha");
       String titulo = request.getParameter("titulo");
       String contenido = request.getParameter("contenido");
       String autor = request.getParameter("autor");
        
       Articulo a = new Articulo();
       a.setId(id);
       a.setFecha(fecha);
       a.setTitulo(titulo);
       a.setContenido(contenido);
       a.setAutor(autor);
       
       conexionDB canal = new conexionDB();
       Connection conn = canal.conectar();
       PreparedStatement ps;
       ResultSet rs;
       
       if(id == 0){
            String sql = "insert into articulos (fecha,titulo,contenido,autor) values (?,?,?,?)";
           try {
               ps = conn.prepareStatement(sql);
               ps.setString(1, a.getFecha());
               ps.setString(2, a.getTitulo());
               ps.setString(3, a.getContenido());
               ps.setString(4, a.getAutor());
          
              ps.executeUpdate();
           } catch (SQLException ex) {
               System.out.println("Error de SQL "+ ex.getMessage());
           }
           finally{
             canal.desconectar();
           }
           response.sendRedirect("MainController");
       }
       else{
           try {
               String sql="update articulos set fecha=?,titulo=?,contenido=?,autor=? where id=?";
               ps = conn.prepareStatement(sql);
               ps.setString(1, a.getFecha());
               ps.setString(2, a.getTitulo());
               ps.setString(3, a.getContenido());
               ps.setString(4, a.getAutor());
               ps.setInt(5, a.getId() );
               ps.executeUpdate();
           } catch (SQLException ex) {
               System.out.println("Error al actualizar "+ex.getMessage());
                      
            }
           response.sendRedirect("MainController");
       }
       
    }

}