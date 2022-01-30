package modelo.persistencia.interfaz;

import java.util.List;

import modelo.entidad.Coche;

/**
 * Interfaz que recoge los métodos CRUD de la entidad Coche
 * 
 * @since 25.01.2022
 *
 */
public interface InterfazDaoCoche {

	/**
	 * Método que añade un Coche a la base de datos
	 * 
	 * @param c Se pasa un objeto coche por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean addCoche(Coche c);

	/**
	 * Método que elimina un Coche por su idCoche de la base de datos
	 * 
	 * @param idCoche Se pasa el idCoche del coche por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean deleteCoche(int idCoche);

	/**
	 * Método que obtiene un Coche por su idCoche de la base de datos
	 * 
	 * @param idCoche Se pasa el idCoche del coche por parámetro
	 * @return Devuelve el Coche con el idCoche solicitado o null si no existe
	 */
	public Coche getCoche(int idCoche);

	/**
	 * Método que modifica un Coche por su idCoche de la base de datos
	 * 
	 * @param c Se pasa un objeto coche por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean updateCoche(Coche c);

	/**
	 * Método que obtine el listado de Coche de la base de datos
	 * 
	 * @return Devuelve un List con los Coche existentes en la base de datos o null
	 *         si no hay ningún Coche
	 */
	public List<Coche> list();
}