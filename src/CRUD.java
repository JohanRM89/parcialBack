import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {
    private Connection conexion;

    public CRUD(String url, String usuario, String contraseña) throws SQLException {
        conexion = DriverManager.getConnection(url, usuario, contraseña);
    }

    public void crearProducto(String nombre) throws SQLException {
        String sql = "INSERT INTO productos (nombre) VALUES (?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.executeUpdate();
        }
    }

    public Producto leerProducto(int id) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Producto(resultSet.getInt("id"), resultSet.getString("nombre"));
            }
        }
        return null;
    }

    public void actualizarProducto(int id, String nuevoNombre) throws SQLException {
        String sql = "UPDATE productos SET nombre = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nuevoNombre);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    public void borrarProducto(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Producto> listarProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                productos.add(new Producto(resultSet.getInt("id"), resultSet.getString("nombre")));
            }
        }
        return productos;
    }

    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}

