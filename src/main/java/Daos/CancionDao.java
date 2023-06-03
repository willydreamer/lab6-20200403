package Daos;

import Beans.Cancion;
import Beans.Tour;

import java.sql.*;
import java.util.ArrayList;

public class CancionDao {

    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";


    public ArrayList<Cancion> listarMasReproducidas() {
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT c.idcancion, c.nombre_cancion, c.banda FROM cancion c left join reproduccion r on c.idcancion = r.cancion_idcancion group by c.idcancion having count(r.idreproduccion) > 2 order by count(*) desc";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
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
    public ArrayList<Cancion> listarTodas() {
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM cancion;";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Cancion cancion = new Cancion();
                cancion.setIdCancion(resultSet.getInt(1));
                cancion.setNombreCancion(resultSet.getString(2));
                cancion.setBanda(resultSet.getString(3));
                cancion.setLike(resultSet.getInt(4));
                lista.add(cancion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public ArrayList<Cancion> listarFavoritas() {
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM lab6sw1.cancion where cancion.like = 1;";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Cancion cancion = new Cancion();
                cancion.setIdCancion(resultSet.getInt(1));
                cancion.setNombreCancion(resultSet.getString(2));
                cancion.setBanda(resultSet.getString(3));
                cancion.setLike(resultSet.getInt(4));
                lista.add(cancion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public void MeGusta(String idCancion) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "update cancion set cancion.like = 1 where idcancion = ?";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idCancion);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void NoMeGusta(String idCancion) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "update cancion set cancion.like = 0 where idcancion = ?";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idCancion);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cancion> listarPorBanda(String idLista) {
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT idcancion, nombre_cancion,banda FROM cancion WHERE banda = ?;";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idLista);
            ResultSet resultSet = stmt.executeQuery();

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