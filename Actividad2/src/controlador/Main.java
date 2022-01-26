package controlador;

import modelo.persistencia.DaoCocheMySQL;

/**
 * Clase que contiene el método main e inicializa el menu de la aplicación.
 * 
 * @since 25.01.2022
 */
public class Main {

	public static void main(String[] args) {

		DaoCocheMySQL dc = new DaoCocheMySQL();

		System.out.println("Iniciando aplicación....");

		// Asignación del DaoCocheMySQL al menu
		new Menu(dc);
	}
}