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
            javax.swing.JOptionPane.showMessageDialog(null, "Error de base de datos:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);

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

    public boolean tienePermisoSelect() {
        try (java.sql.Statement st = conexion.createStatement()) {
            st.executeQuery("SELECT TOP 1 * FROM articulos");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
