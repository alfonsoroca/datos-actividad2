/**
 * 
 */
package modelo.persistencia.interfaz;

import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMySQL;

/**
 * Interfaz que recoge los métodos CRUD de la entidad Pasajero
 * 
 * @since 27.01.2022
 *
 */
public interface InterfazDaoPasajero {

	/**
	 * Método que añade un Pasajero a la base de datos
	 * 
	 * @param p Se pasa un objeto pasajero por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean addPasajero(Pasajero p);

	/**
	 * Método que elimina un Pasajero por su idPasajero de la base de datos
	 * 
	 * @param idPasajero Se pasa el idPasajero por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean deletePasajero(int idPasajero);

	/**
	 * Método que obtiene un Pasajero por su idPasajero de la base de datos
	 * 
	 * @param idPasajero Se pasa el idPasajero por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public Pasajero getPasajero(int idPasajero);

	/**
	 * Método que obtine el listado de Pasajero de la base de datos
	 * 
	 * @return Devuelve un List con los Pasajero existentes en la base de datos o
	 *         null si no hay ningún Coche
	 */
	public List<Pasajero> list();

	/**
	 * Metodo que añade un Pasajero a un Coche
	 * 
	 * @param idPasajero Se pasa el idPasajero por parámetro
	 * @param idCoche    Se pasa el idCoche del coche por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean subirPasajeroCoche(int idPasajero, int idCoche);

	/**
	 * Metodo que elimina un Pasajero de un Coche
	 * 
	 * @param idPasajero Se pasa el idPasajero por parámetro
	 * @param idCoche    Se pasa el idCoche del coche por parámetro
	 * @return Devuelve true si todo es correcto o false si ha habido errores
	 */
	public boolean bajarPasajeroCoche(int idPasajero, int idCoche);

	/**
	 * Método que obtine el listado de Pasajero de un Coche
	 * 
	 * @param idCoche Se pasa el idCoche del coche por parámetro
	 * @return Devuelve un List con los Pasajero asociados al Coche o null si no hay
	 *         ningún Pasajero asociado al Coche
	 */
	public List<Pasajero> listPasajerosCoche(int idCoche);

	/**
	 * Método que imprime por pantalla la relación de Pasajeros / Coches
	 * 
	 * @param dc Se pasa un objeto DaoCocheMySQL
	 */
	public void pasajerosCoches(DaoCocheMySQL dc);
}