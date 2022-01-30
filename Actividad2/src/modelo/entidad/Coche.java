package modelo.entidad;

/**
 * Clase que define los atributos y métodos del objeto coche.
 * 
 * @since 25.01.2022
 */
public class Coche {	
	
	/**
	 * Atributos de los objetos coche
	 */
	private int idCoche;
	private String matricula, marca, modelo, color;

	/**
	 * Constructor de la clase coche.
	 * 
	 * @param idCoche   Identificador único del coche.
	 * @param matricula Matricula del coche.
	 * @param marca     Marca del coche.
	 * @param modelo    Modelo del coche.
	 * @param color     Color del coche.
	 */
	public Coche(int idCoche, String matricula, String marca, String modelo, String color) {
		super();
		this.idCoche = idCoche;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}

	/**
	 * Constructor sin parámetros de la clase coche. 
	 */	
	public Coche() {
		super();
	}

	// Getter y setter
	public int getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// Sobrescritura del método toString()
	@Override
	public String toString() {
		return "Coche [idCoche=" + idCoche + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + "]";
	}
}