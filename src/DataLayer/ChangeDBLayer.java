package DataLayer;


import java.sql.Connection;
import java.sql.Statement;

public class ChangeDBLayer {

    String[] valores;

    private final Connection con;

        public ChangeDBLayer(Connection con, String[] cambios) {
        this.con = con;
        this.valores=cambios;
        execProc();

    }

    public void execProc(){
        String sql = "EXEC sp_MttoArticulos @artID=?, @artnombre=?, @artdescripcion=?, @artprecio=?, @arttamaño=?, @famid=?";
        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, valores[0]);
            pst.setString(2, valores[1]);
            pst.setString(3, valores[2]);
            pst.setString(4, valores[3]);
            pst.setString(5, valores[4]);
            pst.setString(6, valores[5]);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "Operación exitosa. Filas afectadas: " + rowsAffected, "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "No se realizaron cambios.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error ejecutando procedimiento:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
