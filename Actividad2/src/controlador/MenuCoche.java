package controlador;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySQL;

/**
 * Clase que define los atributos y métodos del objeto menuCoche que sirven para
 * interactuar con el usuario de la aplicación.
 * 
 * @since 25.01.2022
 */
public class MenuCoche {

	public MenuCoche(DaoCocheMySQL dc) {

		Scanner sc = new Scanner(System.in);
		String opcion = "";

		// Mediante un bucle while gestionamos la interacción con el usuario
		while (!opcion.equalsIgnoreCase("6")) {

			// Opciones que puede seleccionar el usuario
			System.out.println("\n***************************************");
			System.out.println("---------Almacén concesionario---------");
			System.out.println("***************************************");
			System.out.println("	1. Añadir nuevo coche");
			System.out.println("	2. Borrar coche por id");
			System.out.println("	3. Consultar coche por id");
			System.out.println("	4. Modificar coche por id");
			System.out.println("	5. Listado de coches");
			System.out.println("	6. Terminar programa");
			System.out.println("	7. Acceder a la gestión de pasajeros");
			System.out.println("Elija una opción del 1 al 7 -> ");

			// Lectura de la elección del usuario
			opcion = sc.nextLine();

			// Gestión de la elección del usuario en base a los requerimientos de la
			// aplicación

			Coche coche = null;
			int idCoche = 0;
			boolean execResult = false;

			switch (opcion) {
			case "1":

				System.out.println("Vas a añadir un nuevo coche al almacen...");
				coche = new Coche();
				System.out.println("Introduce la matrícula del coche...");
				coche.setMatricula(sc.nextLine());
				System.out.println("Introduce la marca del coche...");
				coche.setMarca(sc.nextLine());
				System.out.println("Introduce el modelo del coche...");
				coche.setModelo(sc.nextLine());
				System.out.println("Introduce el color del coche...");
				coche.setColor(sc.nextLine());
				// Ejecutamos la operación y mostramos su resultado
				execResult = dc.addCoche(coche);
				if (execResult) {
					System.out.println("	Se ha añadido el coche al almacén.");
				} else {
					System.out.println("	No se ha podido añadir el coche al almacén.");
				}

				break;

			case "2":

				System.out.println("Introduce el id del coche que quieres eliminar...");
				// Ejecutamos la operación y mostramos su resultado
				idCoche = Integer.parseInt(sc.nextLine());
				execResult = dc.deleteCoche(idCoche);
				if (execResult) {
					System.out.println("	Se ha eliminado el coche con id " + idCoche);
				} else {
					System.out.println("	No se ha eliminado el coche con id " + idCoche);
				}
				break;

			case "3":

				System.out.println("Introduce el id del coche que quieres obtener...");
				// Ejecutamos la operación y mostramos su resultado
				idCoche = Integer.parseInt(sc.nextLine());
				coche = dc.getCoche(idCoche);
				if (coche != null) {
					System.out.println("	Has solicitado el " + coche);
				} else {
					System.out.println("	No existe el coche con id " + idCoche);
				}
				break;

			case "4":

				System.out.println("Vas a modificar un coche del almacen...");
				coche = new Coche();
				System.out.println("	El almacén dispone de los siguientes coches:");
				for (Coche c : dc.list()) {
					System.out.println("		" + c);
				}
				System.out.println("Introduce el id del coche a modificar...");
				idCoche = Integer.parseInt(sc.nextLine());
				coche.setIdCoche(idCoche);
				// Validamos la existencia de un coche con ese id
				if (dc.getCoche(coche.getIdCoche()) != null) {
					System.out.println("Introduce la nueva matrícula del coche...");
					coche.setMatricula(sc.nextLine());
					System.out.println("Introduce la nueva marca del coche...");
					coche.setMarca(sc.nextLine());
					System.out.println("Introduce el nuevo modelo del coche...");
					coche.setModelo(sc.nextLine());
					System.out.println("Introduce el nuevo color del coche...");
					coche.setColor(sc.nextLine());
					dc.updateCoche(coche);
					System.out.println("Se han actualizado los datos del coche con id " + idCoche);
				} else {
					System.out.println("Debes introducir un id válido para poder modificar el coche.");
				}
				break;

			case "5":
				System.out.println("Listado de coches en el almacén...");
				// Ejecutamos la operación y mostramos su resultado
				for (Coche c : dc.list()) {
					System.out.println("	" + c);
				}
				break;

			case "6":
				System.out.println("Cerrando la aplicación....");

				// Cierre del scanner
				sc.close();

				System.out.println("Aplicación cerrada---------------");
				System.exit(0);

			case "7":
				System.out.println("Accediendo a la gestión de pasajeros.....");
				new MenuPasajero(dc, sc);
				break;

			default:
				System.out.println("Capullín, debes introducir un número del 1 al 7.");
			}
		}
	}
}