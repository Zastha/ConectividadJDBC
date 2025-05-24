package gUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ArticulosModel extends AbstractTableModel {
    private final String[] columnNames = {"ArtID", "Nombre", "Descripcion", "Precio", "Tamaño", "Familia"};
    private final List<Object[]> datos = new ArrayList<>();

    public ArticulosModel(Connection con) {
        try {
            String selectArticulos = "select * from articulos a inner join familias f on a.famid = f.famid";
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery(selectArticulos);

            while (resultado.next()) {
                System.out.println("man");
                Object[] fila = new Object[] {
                    resultado.getInt("artid"),
                    resultado.getString("artnombre"),
                    resultado.getString("artdescripcion"),
                    resultado.getDouble("artprecio"),
                    resultado.getString("artTamaño"),
                    resultado.getString("famnombre")
                };
                datos.add(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog( 
                null, e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
