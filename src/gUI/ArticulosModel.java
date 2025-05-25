package gUI;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import DataLayer.SelectDBLayer;


import javax.swing.table.AbstractTableModel;

public class ArticulosModel extends AbstractTableModel {
    private final String[] columnNames = {"ArtID", "Nombre", "Descripcion", "Precio", "Tamaño", "Familia"};
    private  List<Object[]> datos = new ArrayList<>();

        public ArticulosModel(Connection con) {
        SelectDBLayer dbLayer = new SelectDBLayer(con);
        this.datos = dbLayer.getArticulos();
        determineSize();
    }

    public ArticulosModel(Connection con, int id){
        SelectDBLayer dbLayer = new SelectDBLayer(con);
            Object[] articulo = dbLayer.getArticuloID(id);
        if (articulo != null) {
        this.datos.add(articulo);
        determineSize();
    }

    }

    public ArticulosModel(Connection con, String fam){
        SelectDBLayer dbLayer = new SelectDBLayer(con);
        this.datos = dbLayer.getArticuloFam(fam);
        determineSize();

    } 


public void determineSize() {
    for (Object[] articulo : this.datos) {
        if (articulo[4] instanceof String) {
            String tam = (String) articulo[4];
            if (!tam.isEmpty()) {
                char c = Character.toUpperCase(tam.charAt(0));
                if (c == 'C') {
                    articulo[4] = "Chico";
                } else if (c == 'M') {
                    articulo[4] = "Mediano";
                } else if (c == 'G') {
                    articulo[4] = "Grande";
                }
            }
        }
    }
    fireTableDataChanged(); // Notifica a la tabla que los datos cambiaron
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
