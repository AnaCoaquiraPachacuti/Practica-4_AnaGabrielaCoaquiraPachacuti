
package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexionDB {
    static String driver="com.mysql.jdbc.Driver";
    static String url="jdbc:mysql://localhost:3306/bd_blog";
    static String usuario="root";
    static String password="";
    
    protected Connection conn = null;
   
    public conexionDB(){
      try{
          Class.forName(driver);
          conn =DriverManager.getConnection(url,usuario,password);
          
          if(conn != null){
              System.out.println("conexion ok"+conn);
          }
      }
       catch (SQLException ex){
          System.out.println("Error de SQL"+ ex.getMessage() );
      
      }
      catch(ClassNotFoundException ex){
          System.out.println("Falta especificar driver"+ex.getMessage());
      }
     
    }
    public Connection conectar(){
     return conn;
    }
    public void desconectar(){
        try {
            System.out.println("Cerrando la base de datos"+conn);
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la base de datos " +ex.getMessage());
        
        }
    }
}
