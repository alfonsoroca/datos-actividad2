package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaz.DaoCoche;

/**
 * Clase que implementa los métodos que definirán el CRUD de la base de datos
 * 
 * @since 25.01.2022
 */
public class DaoCocheMySQL implements DaoCoche {

	private Connection conexion;

// Definimos dos métodos que se encargan de gestionar la conexión y reportar su estado correcto (true) / erroneo (false)

	/**
	 * Método que abre la conexión con la base de datos
	 * 
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean openConnection() {
		String url = "jdbc:mysql://localhost:3306/almacen";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Método que cierra la conexión con la base de datos
	 * 
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean closeConnection() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

// Sobrescribimos cada uno de los métodos de la interfaz que define el CRUD	

	@Override
	public boolean addCoche(Coche c) {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return false;
		}

		// Establecemos un controlador para el resultado de la query
		boolean addCoche = true;

		// Establecemos la query a realizar
		String query = "INSERT INTO COCHES (MATRICULA, MARCA, MODELO, COLOR)" + "VALUES(?,?,?,?);";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setString(4, c.getColor());

			// Ejecutamos la query
			int registros = ps.executeUpdate();

			// Validamos el resultado de la query
			if (registros == 0) {
				addCoche = false;
			}

		} catch (SQLException e) {
			System.out.println("addCoche-> Error al insertar: " + c);
			addCoche = false;
			e.printStackTrace();
			
		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return addCoche;
	}

	@Override
	public boolean deleteCoche(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Coche getCoche(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCoche(Coche c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Coche> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
