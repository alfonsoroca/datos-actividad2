package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaz.InterfazDaoPasajero;

/**
 * Clase que implementa los métodos que definirán el CRUD de la tabla Pasajeros
 * 
 * @since 28.01.2022
 */
public class DaoPasajeroMySQL implements InterfazDaoPasajero {

	private Connection conexion;

// Definimos dos métodos que se encargan de gestionar la conexión y reportar su estado
// correcto (true) / erroneo (false)

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
	public boolean addPasajero(Pasajero p) {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return false;
		}

		// Establecemos un controlador para el resultado de la query
		boolean addPasajero = true;

		// Establecemos la query a realizar
		String query = "INSERT INTO PASAJEROS (NOMBRE, EDAD, PESO)" + "VALUES(?,?,?);";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());

			// Ejecutamos la query
			int registros = ps.executeUpdate();

			// Validamos el resultado de la query
			if (registros == 0) {
				addPasajero = false;
			}

		} catch (SQLException e) {
			System.out.println("	addPasajero-> Error al insertar: " + p);
			addPasajero = false;
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return addPasajero;
	}

	@Override
	public boolean deletePasajero(int idPasajero) {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return false;
		}

		// Establecemos un controlador para el resultado de la query
		boolean deletePasajero = true;

		// Establecemos la query a realizar
		String query = "DELETE FROM PASAJEROS WHERE ID=?;";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);

			// Ejecutamos la query
			int registros = ps.executeUpdate();

			// Validamos el resultado de la query
			if (registros == 0) {
				deletePasajero = false;
			}

		} catch (SQLException e) {
			System.out.println("	deletePasajero-> Error al eliminar el pasajero con id: " + idPasajero);
			deletePasajero = false;
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return deletePasajero;
	}

	@Override
	public Pasajero getPasajero(int idPasajero) {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return null;
		}

		// Establecemos un controlador para el resultado de la query
		Pasajero pasajero = null;

		// Establecemos la query a realizar
		String query = "SELECT ID, NOMBRE, EDAD, PESO FROM PASAJEROS WHERE ID=?;";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);

			// Almacenamos el resultado de la query
			ResultSet rs = ps.executeQuery();

			// Con un while() obtenemos el resultado de la select y creamos un pasajero
			while (rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
			}

		} catch (SQLException e) {
			System.out.println("	getPasajero-> Error al obtener el pasajero con id: " + idPasajero);
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return pasajero;
	}

	@Override
	public List<Pasajero> list() {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return null;
		}

		// Creamos una ArrayList que recogerá los pasajeros de la base de datos
		List<Pasajero> listaPasajeros = new ArrayList<>();

		// Establecemos la query a realizar
		String query = "SELECT ID, NOMBRE, EDAD, PESO FROM PASAJEROS;";

		try {
			// Preparamos la sentencia de la query
			PreparedStatement ps = conexion.prepareStatement(query);

			// Almacenamos el resultado de la query
			ResultSet rs = ps.executeQuery();

			// Con un while() obtenemos el resultado de la select, creamos un pasajero y lo
			// añadimos a la listaPasajeros
			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));

				// Añadimos el pasajero a la listaPasajeros
				listaPasajeros.add(pasajero);
			}

		} catch (SQLException e) {
			System.out.println("	list-> Error al obtener la lista de pasajeros.");
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return listaPasajeros;
	}

	@Override
	public boolean subirPasajeroCoche(int idPasajero, int idCoche) {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return false;
		}

		// Establecemos un controlador para el resultado de la query
		boolean subirPasajero = true;

		// Establecemos la query a realizar
		String query = "INSERT INTO COCHES_PASAJEROS (IDCOCHE, IDPASAJERO) VALUES (?,?);";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);

			// Ejecutamos la query
			int registros = ps.executeUpdate();

			// Validamos el resultado de la query
			if (registros == 0) {
				subirPasajero = false;
			}

		} catch (SQLException e) {
			subirPasajero = false;
			System.out.println("	subirPasajeroCoche-> Error al subir el pasajero con id " + idPasajero
					+ " al coche con id " + idCoche);
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return subirPasajero;
	}

	@Override
	public boolean bajarPasajeroCoche(int idPasajero, int idCoche) {
		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return false;
		}

		// Establecemos un controlador para el resultado de la query
		boolean bajarPasajero = true;

		// Establecemos la query a realizar
		String query = "DELETE FROM COCHES_PASAJEROS WHERE IDCOCHE=? AND IDPASAJERO=?;";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);

			// Ejecutamos la query
			int registros = ps.executeUpdate();

			// Validamos el resultado de la query
			if (registros == 0) {
				bajarPasajero = false;
			}

		} catch (SQLException e) {
			bajarPasajero = false;
			System.out.println("	bajarPasajeroCoche-> Error al bajar el pasajero con id " + idPasajero
					+ " del coche con id " + idCoche);
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return bajarPasajero;
	}

	@Override
	public List<Pasajero> listPasajerosCoche(int idCoche) {
		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return null;
		}

		// Creamos una ArrayList que recogerá los pasajeros de la base de datos
		List<Pasajero> listaPasajerosCoche = new ArrayList<>();

		// Establecemos la query a realizar
		String query = "SELECT IDPASAJERO FROM COCHES_PASAJEROS WHERE IDCOCHE=?;";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);

			// Almacenamos el resultado de la query
			ResultSet rs = ps.executeQuery();

			// Con un while() obtenemos el resultado de la select, creamos un pasajero y lo
			// añadimos a la listaPasajerosCoche
			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero = this.getPasajero(rs.getInt(1));
				listaPasajerosCoche.add(pasajero);
			}

		} catch (SQLException e) {
			System.out.println("	listPasajerosCoche-> Error al obtener la lista de pasajeros del coche " + idCoche);
			e.printStackTrace();
		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return listaPasajerosCoche;
	}

	@Override
	public void pasajerosCoches(DaoCocheMySQL dc) {

		// Comprobamos el estado de la conexión a la base de datos
		if (openConnection()) {

			// Establecemos la query a realizar
			String query = "SELECT * FROM COCHES_PASAJEROS;";

			try {
				// Preparamos la sentencia de la query con los parámetros
				PreparedStatement ps = conexion.prepareStatement(query);

				// Almacenamos el resultado de la query
				ResultSet rs = ps.executeQuery();

				// Con un while() obtenemos el resultado de la select e imprimimos el resultado
				// por consola
				while (rs.next()) {
					System.out.println("	" + this.getPasajero(rs.getInt(2)) + " / " + dc.getCoche(rs.getInt(1)));
				}

			} catch (SQLException e) {
				System.out.println("	pasajerosCoches-> Error al obtener el listado de Pasajeros / Coches.");
				e.printStackTrace();
			} finally {
				// Cerramos la conexion a la base de datos
				closeConnection();
			}
		}
	}
}