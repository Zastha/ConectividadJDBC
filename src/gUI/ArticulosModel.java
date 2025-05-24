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
    }

    public ArticulosModel(Connection con, int id){
        SelectDBLayer dbLayer = new SelectDBLayer(con);
            Object[] articulo = dbLayer.getArticuloID(id);
        if (articulo != null) {
        this.datos.add(articulo);
    }

    }

    public ArticulosModel(Connection con, String fam){
        SelectDBLayer dbLayer = new SelectDBLayer(con);
        this.datos = dbLayer.getArticuloFam(fam);

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

    public void consultaID(){
        
    }

    public void consultaFam(){
        
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
