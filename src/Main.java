import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nombre_base_de_datos";
        String usuario = "tu_usuario";
        String contraseña = "tu_contraseña";

        try {
            CRUD crud = new CRUD(url, usuario, contraseña);

            // Crear producto
            crud.crearProducto("Producto 1");

            // Listar productos
            System.out.println("Productos:");
            for (Producto producto : crud.listarProductos()) {
                System.out.println(producto.getId() + ": " + producto.getNombre());
            }

            // Leer un producto
            Producto producto = crud.leerProducto(1);
            System.out.println("\nProducto con id 1:");
            if (producto != null) {
                System.out.println(producto.getId() + ": " + producto.getNombre());
            } else {
                System.out.println("Producto no encontrado");
            }

            // Actualizar un producto
            crud.actualizarProducto(1, "Nuevo Nombre");

            // Borrar un producto
            crud.borrarProducto(1);

            // Listar productos actualizados
            System.out.println("\nProductos actualizados:");
            for (Producto p : crud.listarProductos()) {
                System.out.println(p.getId() + ": " + p.getNombre());
            }

            // Cerrar conexión
            crud.cerrarConexion();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
