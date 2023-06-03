package Daos;

import Beans.Cancion;
import Beans.Tour;

import java.sql.*;
import java.util.ArrayList;

public class TourDao {

    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";


    public ArrayList<Tour> obtenerListaTours(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Tour> listaTours = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from tour where nombre_tour like '%world%'")) {

            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String banda = rs.getString(3);

                listaTours.add(new Tour(id,nombre,banda));
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        return listaTours;
    }
    public ArrayList<Cancion> listarMasReproducidas() {
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT c.idcancion, c.nombre_cancion, c.banda FROM cancion c left join reproduccion r on c.idcancion = r.cancion_idcancion group by c.idcancion having count(r.idreproduccion) > 2 order by count(*) desc";
        try (Connection connection = DriverManager.getConnection(url, "root", pass);
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Cancion cancion = new Cancion();
                cancion.setIdCancion(resultSet.getInt(1));
                cancion.setNombreCancion(resultSet.getString(2));
                cancion.setBanda(resultSet.getString(3));
                lista.add(cancion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
