package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaz.InterfazDaoCoche;

/**
 * Clase que implementa los métodos que definirán el CRUD de la tabla Coches
 * 
 * @since 25.01.2022
 */
public class DaoCocheMySQL implements InterfazDaoCoche {

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
			System.out.println("No se ha podido conectar con la base de datos.");
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
			System.out.println("	addCoche-> Error al insertar el " + c);
			addCoche = false;
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return addCoche;
	}

	@Override
	public boolean deleteCoche(int idCoche) {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return false;
		}

		// Establecemos un controlador para el resultado de la query
		boolean deleteCoche = true;

		// Establecemos la query a realizar
		String query = "DELETE FROM COCHES WHERE ID=?;";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);

			// Ejecutamos la query
			int registros = ps.executeUpdate();

			// Validamos el resultado de la query
			if (registros == 0) {
				deleteCoche = false;
			}

		} catch (SQLException e) {
			System.out.println("	deleteCoche-> Error al eliminar el coche con id: " + idCoche);
			deleteCoche = false;
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return deleteCoche;
	}

	@Override
	public Coche getCoche(int idCoche) {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return null;
		}

		// Instanciamos un coche que será retornado por el método
		Coche coche = null;

		// Establecemos la query a realizar
		String query = "SELECT ID, MATRICULA, MARCA, MODELO, COLOR FROM COCHES WHERE ID=?;";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);

			// Almacenamos el resultado de la query
			ResultSet rs = ps.executeQuery();

			// Con un while() obtenemos el resultado de la select y creamos un coche
			while (rs.next()) {
				coche = new Coche();
				coche.setIdCoche(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setColor(rs.getString(5));
			}

		} catch (SQLException e) {
			System.out.println("	getCoche-> Error al obtener el coche con id: " + idCoche);
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return coche;
	}

	@Override
	public boolean updateCoche(Coche c) {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return false;
		}

		// Establecemos un controlador para el resultado de la query
		boolean updateCoche = true;

		// Establecemos la query a realizar
		String query = "UPDATE COCHES SET MATRICULA=?, MARCA=?, MODELO=?, COLOR=? WHERE ID=?;";

		try {
			// Preparamos la sentencia de la query con los parámetros
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setString(4, c.getColor());
			ps.setInt(5, c.getIdCoche());

			// Ejecutamos la query
			int registros = ps.executeUpdate();

			// Validamos el resultado de la query
			if (registros == 0) {
				updateCoche = false;
			}

		} catch (SQLException e) {
			updateCoche = false;
			System.out.println("	updateCoche-> Error al actualizar el " + c);
			e.printStackTrace();

		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return updateCoche;
	}

	@Override
	public List<Coche> list() {

		// Comprobamos el estado de la conexión a la base de datos
		if (!openConnection()) {
			return null;
		}

		// Creamos una ArrayList que recogerá los coches de la base de datos
		List<Coche> listaCoches = new ArrayList<>();

		// Establecemos la query a realizar
		String query = "SELECT ID, MATRICULA, MARCA, MODELO, COLOR FROM COCHES;";

		try {
			// Preparamos la sentencia de la query
			PreparedStatement ps = conexion.prepareStatement(query);

			// Almacenamos el resultado de la query
			ResultSet rs = ps.executeQuery();

			// Con un while() obtenemos el resultado de la select, creamos un coche y lo
			// añadimos a la listaCoches
			while (rs.next()) {
				Coche coche = new Coche();
				coche.setIdCoche(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setColor(rs.getString(5));

				// Añadimos el coche a la listaCoches
				listaCoches.add(coche);
			}

		} catch (SQLException e) {
			System.out.println("	list-> Error al obtener la lista de coches.");
			e.printStackTrace();
		} finally {
			// Cerramos la conexion a la base de datos
			closeConnection();
		}

		return listaCoches;
	}
}