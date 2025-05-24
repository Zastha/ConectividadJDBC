package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDBLayer {
    private int mensaje;

    Connection conexion;


    public ConnectDBLayer(String server, String DB, String nombreUser, String pass){
        String url = "jdbc:sqlserver://"+server+":1433;databaseName="+DB+";encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error conectandose con SQL Server");
            e.printStackTrace();

        }


        try{
            Connection con = DriverManager.getConnection(url,nombreUser,pass);
            mensaje=999;
            conexion=con;

        }catch (SQLException e){
            System.out.println(e.getErrorCode());
            mensaje = e.getErrorCode();



        }

    }

    public void probarPermiso(){

    }

    public int getEstado(){
        return this.mensaje;
    }

    public Connection getConexion(){
        System.out.println("Yo");
        
        return this.conexion;
    }
}
