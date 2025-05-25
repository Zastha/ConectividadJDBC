package DataLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectDBLayer {
    private final Connection con;

    public SelectDBLayer(Connection con) {
        this.con = con;
    }

    public List<Object[]> getArticulos() {
        List<Object[]> datos = new ArrayList<>();
        String sql = "SELECT * FROM articulos a INNER JOIN familias f ON a.famid = f.famid";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Object[] fila = new Object[] {
                    rs.getInt("artid"),
                    rs.getString("artnombre"),
                    rs.getString("artdescripcion"),
                    rs.getDouble("artprecio"),
                    rs.getString("artTamaño"),
                    rs.getString("famnombre")
                };
                datos.add(fila);
            }
        } catch (Exception e) {
           javax.swing.JOptionPane.showMessageDialog(null, "Error Generando la tabla de datos:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }

    public Object[] getArticuloID(int id){
        Object[] articulo = null;
        String sql = "SELECT * FROM articulos a INNER JOIN familias f ON a.famid = f.famid WHERE a.artid = "+id;
        try{
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(sql);
             

            if(rs.next()){
                 articulo = new Object[] {
                    rs.getInt("artid"),
                    rs.getString("artnombre"),
                    rs.getString("artdescripcion"),
                    rs.getDouble("artprecio"),
                    rs.getString("artTamaño"),
                    rs.getString("famnombre")

            };
                
                }

        }catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al buscar articulo por id:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
    e.printStackTrace();
        }
        return articulo;


    }

        public List<Object[]> getArticuloFam(String famNombre){
        List<Object[]> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos a INNER JOIN familias f ON a.famid = f.famid WHERE f.famnombre= '"+famNombre+"'";

           try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Object[] fila = new Object[] {
                    rs.getInt("artid"),
                    rs.getString("artnombre"),
                    rs.getString("artdescripcion"),
                    rs.getDouble("artprecio"),
                    rs.getString("artTamaño"),
                    rs.getString("famnombre")
                };
                articulos.add(fila);
            }
        } catch (Exception e) {
           javax.swing.JOptionPane.showMessageDialog(null, "Error al filtrar por familias:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return articulos;


    }

    public ArrayList<String> getListaFamilias() {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT famnombre FROM familias";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("famnombre"));
            }
        } catch (Exception e) {
           javax.swing.JOptionPane.showMessageDialog(null, "Error al buscar listado de familias:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }


    public String getFamiliaID(String famNombre){
        String famID = "";
        String sql = "SELECT famid FROM familias WHERE famnombre = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, famNombre);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    famID = rs.getString("famid");
                }
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al buscar FamiliaID:\n" + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return famID;
    }
}