package Daos;

import Beans.Cancion;
import Beans.Lista;

import java.sql.*;
import java.util.ArrayList;

public class ListasDao {
    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";

    public ArrayList<Lista> listarTodas() {
        ArrayList<Lista> listasReproduccion = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM lab6sw1.lista;";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Lista lista1 = new Lista();
                lista1.setIdLista(resultSet.getInt(1));
                lista1.setNameLista(resultSet.getString(2));
                listasReproduccion.add(lista1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listasReproduccion;
    }

    public static void crearLista(String nameLista) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO lista (namelista) VALUES (?);";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nameLista);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrar(String listaId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "DELETE FROM lista WHERE idlista = ?";
        try (Connection connection = DriverManager.getConnection(url, user,pass);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, listaId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cancion> listaInspeccionar(String idLista) {
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM cancion where cancion.like = 0;";
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

    public Lista obtenerListaEspecifica(int idListaEspecifica) {
        Lista listaEspecifica = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM lab6sw1.lista WHERE idlista = ?;";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idListaEspecifica);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                listaEspecifica = new Lista();
                listaEspecifica.setIdLista(resultSet.getInt(1));
                listaEspecifica.setNameLista(resultSet.getString(2));
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaEspecifica;
    }

    public void anadirCancionAlista(String idCancion,String idLista) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO canciones_lista (idcancion, idlista) VALUES (?, ?);";
        try (Connection connection = DriverManager.getConnection(url, user,pass);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, idCancion);
            pstmt.setString(2, idLista);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarCancionAlista(String idCancion,String idLista) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "DELETE FROM canciones_lista WHERE idcancion = ? AND idlista = ?";
        try (Connection connection = DriverManager.getConnection(url, user,pass);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, idCancion);
            pstmt.setString(2, idLista);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cancion> obtenerCancionesFavoritasPorReproductor(String idLista) {
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT c.*\n" +
                "FROM canciones_lista cl\n" +
                "JOIN cancion c ON cl.idcancion = c.idcancion\n" +
                "WHERE cl.idlista = ?";

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idLista);
            ResultSet resultSet = stmt.executeQuery();

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
}
