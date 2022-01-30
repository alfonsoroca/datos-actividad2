/**
 * 
 */
package modelo.entidad;

/**
 * Clase que define los atributos y métodos del objeto pasajero.
 * 
 * @since 27.01.2022
 *
 */
public class Pasajero {

	private int id, edad;
	private String nombre;
	private double peso;

	/**
	 * Constructor de la clase pasajero.
	 * 
	 * @param id     Identificador único del pasajero.
	 * @param nombre Nombre del pasajero.
	 * @param edad   Edad del pasajero.
	 * @param peso   Peso del pasajero. *
	 */
	public Pasajero(int id, String nombre, int edad, double peso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}

	/**
	 * Constructor sin parámetros de la clase pasajero.
	 */
	public Pasajero() {
		super();
	}

	// Getter y setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	// Sobrescritura del método toString()
	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + "]";
	}
}
