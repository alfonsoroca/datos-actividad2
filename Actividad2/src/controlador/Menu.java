package controlador;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySQL;

/**
 * Clase que define los atributos y métodos del objeto menu que sirven para
 * interactuar con el usuario de la aplicación.
 * 
 * @since 25.01.2022
 */
public class Menu {

	public Menu(DaoCocheMySQL dc) {

		Scanner sc = new Scanner(System.in);
		String opcion = "";

		// Mediante un bucle while gestionamos la interacción con el usuario
		while (!opcion.equalsIgnoreCase("5")) {

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
			
			Coche coche;
			
			switch (opcion) {
			case "1":

				System.out.println("Vas a añadir un nuevo coche al almacen...");
				coche = new Coche();
				System.out.println("Introduce el id del coche...");
				coche.setId(Integer.parseInt(sc.nextLine()));
				System.out.println("Introduce la matrícula del coche...");
				coche.setMatricula(sc.nextLine());
				System.out.println("Introduce la marca del coche...");
				coche.setMarca(sc.nextLine());
				System.out.println("Introduce el modelo del coche...");
				coche.setModelo(sc.nextLine());
				System.out.println("Introduce el color del coche...");
				coche.setColor(sc.nextLine());
				dc.addCoche(coche);
				break;

			case "2":

				System.out.println("Introduce el id del coche que quieres eliminar...");
				dc.deleteCoche(Integer.parseInt(sc.nextLine()));
				break;

			case "3":

				System.out.println("Introduce el id del coche que quieres obtener...");
				dc.getCoche(Integer.parseInt(sc.nextLine()));
				break;
	
			case "4":
				
				System.out.println("Vas a modificar un coche del almacen...");
				coche = new Coche();
				System.out.println("Introduce el id del coche a modificar...");
				coche.setId(Integer.parseInt(sc.nextLine()));
				System.out.println("Introduce la nueva matrícula del coche...");
				coche.setMatricula(sc.nextLine());
				System.out.println("Introduce la nueva marca del coche...");
				coche.setMarca(sc.nextLine());
				System.out.println("Introduce el nuevo modelo del coche...");
				coche.setModelo(sc.nextLine());
				System.out.println("Introduce el nuevo color del coche...");
				coche.setColor(sc.nextLine());
				dc.updateCoche(coche);
				break;
			
			case "5":
				System.out.println("Listado de coches en el almacén...");
				dc.list();
				break;			

			case "6":
				System.out.println("Cerrando la aplicación....");
				
				// Cierre del scanner
				sc.close();

				System.out.println("Aplicación cerrada---------------");
				System.exit(0);

			case "7":
				System.out.println("Acceder a la gestión de pasajeros");
				
				break;

			default:
				System.out.println("Capullín, debes introducir un número del 1 al 6.");
			}
		}
	}
}