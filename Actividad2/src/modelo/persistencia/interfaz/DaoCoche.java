package modelo.persistencia.interfaz;

import java.util.List;

import modelo.entidad.Coche;

/**
 * Interfaz que recoge los métodos CRUD de la entidad Coche
 * 
 * @since 25.01.2022
 *
 */

public interface DaoCoche {

	/**
	 * Interfaz para el método que añade un Coche a la base de datos
	 * 
	 * @param c Se pasa un objeto coche por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean addCoche(Coche c);

	/**
	 * Interfaz para el método que elimina un Coche por su id de la base de datos
	 * 
	 * @param id Se pasa el id del coche por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean deleteCoche(int id);

	/**
	 * Interfaz para el método que obtiene un Coche por su id de la base de datos
	 * 
	 * @param id Se pasa el id del coche por parámetro
	 * @return Devuelve el Coche con el id solicitado o null si no existe
	 */
	public Coche getCoche(int id);

	/**
	 * Interfaz para el método que modifica un Coche por su id de la base de datos
	 * 
	 * @param c Se pasa un objeto coche por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean updateCoche(Coche c);

	/**
	 * Interfaz para el método que obtine el listado de Coche de la base de datos
	 * 
	 * @return
	 */
	public List<Coche> list();
}