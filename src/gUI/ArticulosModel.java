package gUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import DataLayer.SelectDBLayer;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ArticulosModel extends AbstractTableModel {
    private final String[] columnNames = {"ArtID", "Nombre", "Descripcion", "Precio", "Tamaño", "Familia"};
    private  List<Object[]> datos = new ArrayList<>();

        public ArticulosModel(Connection con) {
        SelectDBLayer dbLayer = new SelectDBLayer(con);
        this.datos = dbLayer.getArticulos();
    }


    public String determineSize(String tam){
        

        if(tam.charAt(0) == 'C'){
            return "Chico";
        }

        if(tam.charAt(0) == 'M'){
            return "Mediano";
        }

        if(tam.charAt(0)== 'G'){
            return "Grande";
        }
        
        return tam;

    }

    public void consultaEspecifica(){

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
