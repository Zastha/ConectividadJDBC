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

    public void execProc() {
        String sql = "{call sp_MttoArticulos(?, ?, ?, ?, ?, ?)}";
        try (java.sql.CallableStatement cst = con.prepareCall(sql)) {
            cst.setString(1, valores[0]); 
            cst.setString(2, valores[1]);
            cst.setString(3, valores[2]);
            cst.setString(4, valores[3]);
            cst.setString(5, valores[4]);
            cst.setString(6, valores[5]);

          
            cst.registerOutParameter(1, java.sql.Types.INTEGER);

            int rowsAffected = cst.executeUpdate();

            int idGenerado = cst.getInt(1);

            if (rowsAffected > 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "Operación exitosa. ID afectado: " + idGenerado, "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "No se realizaron cambios.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error ejecutando procedimiento:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
