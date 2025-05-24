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
            e.printStackTrace();
        }
        return datos;
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
            e.printStackTrace();
        }
        return lista;
    }
}