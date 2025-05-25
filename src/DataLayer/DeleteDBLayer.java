package DataLayer;
import java.sql.Connection;
import java.sql.Statement;

public class DeleteDBLayer {
    private final Connection con;
    public DeleteDBLayer(Connection con,int artid){
        this.con=con;
        eliminarArticulo(artid);
    }

    public void eliminarArticulo(int artid){

        String sql = "DELETE FROM articulos where artid = "+artid;

        try{
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            int filas = pst.executeUpdate();

          if (filas > 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "Artículo con id "+artid+" eliminado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No se encontró el artículo con id "+artid, "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
        }

        }catch(Exception e){
javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }


    }
    
}
