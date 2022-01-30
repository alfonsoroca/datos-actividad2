package controlador;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMySQL;
import modelo.persistencia.DaoPasajeroMySQL;

/**
 * Clase que define los atributos y métodos del objeto menuPasajero que sirven
 * para interactuar con el usuario de la aplicación.
 * 
 * @since 25.01.2022
 */
public class MenuPasajero {

	public MenuPasajero(DaoCocheMySQL dc, Scanner sc) {

		DaoPasajeroMySQL dp = new DaoPasajeroMySQL();
		String opcion = "";

		// Mediante un bucle while gestionamos la interacción con el usuario
		while (!opcion.equalsIgnoreCase("8")) {

			// Opciones que puede seleccionar el usuario
			System.out.println("\n***************************************");
			System.out.println("---------------Pasajeros---------------");
			System.out.println("***************************************");
			System.out.println("	1. Añadir nuevo pasajero");
			System.out.println("	2. Borrar pasajero por id");
			System.out.println("	3. Consultar pasajero por id");
			System.out.println("	4. Listado de pasajeros");
			System.out.println("	5. Añadir pasajero a coche");
			System.out.println("	6. Quitar pasajero de coche");
			System.out.println("	7. Listado de pasajeros en un coche");
			System.out.println("	8. Salir del menú de pasajeros");
			System.out.println("Elija una opción del 1 al 8 -> ");

			// Lectura de la elección del usuario
			opcion = sc.nextLine();

			// Gestión de la elección del usuario en base a los requerimientos de la
			// aplicación

			Pasajero pasajero = null;
			int idPasajero = 0;
			int idCoche = 0;
			boolean execResult = false;

			switch (opcion) {
			case "1":

				System.out.println("Vas a añadir un nuevo pasajero al listado...");
				pasajero = new Pasajero();
				System.out.println("Introduce el nombre del pasajero...");
				pasajero.setNombre(sc.nextLine());
				System.out.println("Introduce la edad del pasajero...");
				pasajero.setEdad(Integer.parseInt(sc.nextLine()));
				System.out.println("Introduce el peso del pasajero...");
				pasajero.setPeso(Double.parseDouble(sc.nextLine()));
				// Ejecutamos la operación y mostramos su resultado
				execResult = dp.addPasajero(pasajero);
				if (execResult) {
					System.out.println("	Se ha añadido el pasajero");
				} else {
					System.out.println("	No se ha podido añadir el pasajero");
				}

				break;

			case "2":

				System.out.println("Introduce el id del pasajero que quieres eliminar...");
				// Ejecutamos la operación y mostramos su resultado
				idPasajero = Integer.parseInt(sc.nextLine());
				execResult = dp.deletePasajero(idPasajero);
				if (execResult) {
					System.out.println("	Se ha eliminado el pasajero con id " + idPasajero);
				} else {
					System.out.println("	No se ha eliminado el pasajero con id " + idPasajero);
				}
				break;

			case "3":

				System.out.println("Introduce el id del pasajero que quieres obtener...");
				// Ejecutamos la operación y mostramos su resultado
				idPasajero = Integer.parseInt(sc.nextLine());
				pasajero = dp.getPasajero(idPasajero);
				if (pasajero != null) {
					System.out.println("	Has solicitado el " + pasajero);
				} else {
					System.out.println("	No existe el pasajero con id " + idPasajero);
				}
				break;

			case "4":

				System.out.println("Listado de pasajeros...");
				// Ejecutamos la operación y mostramos su resultado
				for (Pasajero p : dp.list()) {
					System.out.println("	" + p);
				}
				break;

			case "5":

				System.out.println("Vas a añadir un pasajero a un coche...");
				// Mostramos coches disponibles
				System.out.println("Los coches disponibles en este momento son los siguientes:");
				for (Coche c : dc.list()) {
					System.out.println("	" + c);
				}
				System.out.println("Introduce el id del coche al quieres añadir al pasajero....");
				idCoche = Integer.parseInt(sc.nextLine());
				// Mostramos los pasajeros del coche seleccionado
				System.out.println("El " + dc.getCoche(idCoche) + " tiene los siguientes pasajeros:");
				if (dp.listPasajerosCoche(idCoche).isEmpty()) {
					System.out.println("	El coche se encuentra vacío.");
				} else {
					for (Pasajero p : dp.listPasajerosCoche(idCoche)) {
						System.out.println("	" + p);
					}
				}
				// Mostramos los pasajeros disponibles
				System.out.println("Los pasajeros disponibles son los siguientes:");
				for (Pasajero p : dp.list()) {
					System.out.println("	" + p);
				}
				System.out.println("Introduce el id del pasajero que quieres añadir....");
				idPasajero = Integer.parseInt(sc.nextLine());

				// Ejecutamos la operación y mostramos su resultado
				if (dp.subirPasajeroCoche(idPasajero, idCoche)) {
					System.out.println(
							"	Se ha subido el  " + dp.getPasajero(idPasajero) + " al " + dc.getCoche(idCoche));
				} else {
					System.out.println("	No se ha podido subir el " + dp.getPasajero(idPasajero) + " al "
							+ dc.getCoche(idCoche));
				}
				break;

			case "6":

				System.out.println("Vas a quitar un pasajero de un coche...");
				System.out.println("Los pasajeros / coches son los siguientes:");
				dp.cochesPasajeros();
				
				System.out.println("Introduce el id del pasajero que quieres quitar....");
				idPasajero = Integer.parseInt(sc.nextLine());
				
				System.out.println("Introduce el id del coche del que quieres quitar al pasajero....");
				idCoche = Integer.parseInt(sc.nextLine());
				// Ejecutamos la operación y mostramos su resultado
				if (dp.bajarPasajeroCoche(idPasajero, idCoche)) {
					System.out.println(
							"	Se ha bajado el pasajero con id " + idPasajero + " del coche con id " + idCoche);
				} else {
					System.out.println("	No se ha podido bajar el pasajero con id " + idPasajero
							+ " del coche con id " + idCoche);
				}
				break;

			case "7":
				System.out.println("Para obtener el listado de pasajeros en un coche introduce el id del coche...");
				// Ejecutamos la operación y mostramos su resultado
				idCoche = Integer.parseInt(sc.nextLine());
				if (dc.getCoche(idCoche) != null) {
					System.out.println("El " + dc.getCoche(idCoche) + " tiene los siguientes pasajeros:");
					if (dp.listPasajerosCoche(idCoche).isEmpty()) {
						System.out.println("	El coche se encuentra vacío.");
					} else {
						for (Pasajero p : dp.listPasajerosCoche(idCoche)) {
							System.out.println("	" + p);
						}
					}
				} else {
					System.out.println("No existe el coche con id " + idCoche);
				}

				break;

			case "8":
				System.out.println("Saliendo del menú de pasajeros....");
				break;

			default:
				System.out.println("Capullín, debes introducir un número del 1 al 8.");
			}
		}
	}
}