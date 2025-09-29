package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.MenuPrincipal;
import ar.edu.unlam.pb1.dominio.Planta;
import ar.edu.unlam.pb1.dominio.TipoDePlanta;
import ar.edu.unlam.pb1.dominio.Vivero;

public class GestionDeVivero {
	private static final int SALIR = 99;
	private static final String MENU_TIPO_DE_PLANTA = "\n\nIngrese el tipo de planta:\n1 - Hierba\n2 - Mata\n3 - Arbusto\n4 - Arbol";
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		int numeroIngresado = 0;
		MenuPrincipal opcionMenuPrincipal = null;

		String nombreVivero = ingresarString("\nIngrese el nombre del vivero");

		int cantidadMacetas = ingresarNumeroEntero("\nIngrese la cantidad maxima de macetas");

		Vivero vivero = new Vivero(nombreVivero, cantidadMacetas);

		mostrarPorPantalla("\n\nLa contrasenia generada es: " + vivero.getContrasenia());

		boolean sesionIniciada = false;

		String contraseniaIngresada = "";

		do {
			mostrarPorPantalla("\n Ingrese la contrasenia para continuar:");
			// TODO: completar el inicio de sesion y la validacion
			contraseniaIngresada = teclado.next();
			sesionIniciada = vivero.iniciarSesion(contraseniaIngresada);
			if (!sesionIniciada) {
				mostrarPorPantalla("contrasenia incorrecta");
			}

		} while (!sesionIniciada);

		do {
			mostrarPorPantalla("\n\n################################\nVivero " + vivero.getNombre());
			mostrarMenuPrincipal();
			numeroIngresado = ingresarNumeroEntero("\n\nIngrese opcion:");

			// TODO: Obtener la opcion del menu principal indicada por el usuario.
			// Considerar el caso de ingresar 99 (Salir)
			opcionMenuPrincipal = obtenerOpcionDeMenuPrincipal(numeroIngresado);

			switch (opcionMenuPrincipal) {
			case AGREGAR_PLANTA: {
				// TODO: Ingresar una planta utilizando el metodo ingresarPlanta() y luego
				// mostrar si fue posible o no realizar la accion
				mostrarPorPantalla("\n-----Agregar planta-----");
				Planta planta = ingresarPlanta();
				boolean plantaAgregada = vivero.agregarPlanta(planta);
				if (plantaAgregada) {
					System.out.println("Se agrego correctamente la planta!!!!!");
				} else {
					System.err.println("No fue posible agregar la planta");
				}

				break;
			}
			case MODIFICAR_STOCK_PLANTA: {
				// TODO: Solicitar el codigo y nuevo stock de la planta a la cual se le quiere
				// actualizar el stock e indicar con mensajes si fue posible realizar la
				// operacion
				mostrarPorPantalla("\n-----Modificar stock de la planta-----");
				int codigo = ingresarNumeroEntero("ingrese el codigo de la planta a modificar");
				int stock = ingresarNumeroEntero("Ingrese el nuevo stock de la planta");
				boolean modificado = vivero.modificarStockDePlantaPorCodigo(codigo, stock);
				if (modificado) {
					System.out.println("Fue posible modificar el stock!!!!");
				} else {
					System.err.println("Hubo un error, puede ser que escribio mal el codigo o la planta no existe");
				}

				break;
			}
			case BUSCAR_PLANTA_POR_CODIGO: {
				// TODO: Ingresar el codigo de la planta para buscarla, en caso de existir
				// mostrarla, caso contrario indicar con un mensaje que no se encontro
				mostrarPorPantalla("\n-----Buscar planta por codigo-----");
				int codigo = ingresarNumeroEntero("ingrese el codigo de la planta para buscarla");
				Planta plantaCodigo = vivero.buscarPlantaPorCodigo(codigo);
				if (plantaCodigo != null) {
					System.out.println(plantaCodigo.toString());
				} else {
					System.err.println("No fue encontrada la planta o no existe");
				}

				break;
			}
			case BUSCAR_PLANTAS_QUE_CONTIENEN: {
				// TODO: Ingresar el texto a buscar en los nombres de las plantas y mostrar las
				// que contenga el texto (se provee de un metodo mostrarPlantas())
				mostrarPorPantalla("\n-----Buscar la planta por nombre especifico");
				String texto = ingresarString("ingrese el nombre de la planta que desee buscar");
				Planta[] plantaEncontrada = vivero.buscarPlantasQueContienen(texto);
				if (plantaEncontrada.length > 0) {
					mostrarPlantas(plantaEncontrada);
				} else {
					System.err.println("No se encontro la planta que busca o esta no exite");
				}
				break;
			}
			case VENDER_PLANTA: {
				// TODO: Se debe solicitar el ingreso del codigo y cantidad a vender de la
				// planta. Mostrar un mensaje de exito o error segun corresponda
				mostrarPorPantalla("\n-----Vender una planta-----");
				int codigo = ingresarNumeroEntero("ingrese el codigo de la planta a comprar");
				int cantidadAVender = ingresarNumeroEntero("ingrese la cantidad de plantas a comprar");
				boolean venderPlanta = vivero.venderPlanta(codigo, cantidadAVender);
				if (venderPlanta) {
					System.out.println("Se vendio con exito la planta!!!!");
				} else {
					System.err.println("Error de venta");
				}
				break;
			}
			case OBTENER_PLANTAS_DEL_TIPO: {
				// TODO: Ingresar el tipo de planta utilizando el metodo
				// ingresarTipoDePlanta(MENU_TIPO_DE_PLANTA). Luego se debe obtener las plantas
				// de ese tipo y mostrarlas (se provee de un metodo mostrarPlantas())
				mostrarPorPantalla("\n-----Obetener planta del tipo-----");
				TipoDePlanta tipoDePlanta = ingresarTipoDePlanta(MENU_TIPO_DE_PLANTA);
				Planta[] tipo = vivero.obtenerPlantasDeTipoOrdenadasPorNombreAscendende(tipoDePlanta);
				if (tipo.length > 0) {
					mostrarPlantas(tipo);
				} else {
					System.err.println("No se encontro la planta o esta no exite");
				}
				break;
			}
			case SALIR: {
				// TODO: Antes de salir, mostrar el estado actual del vivero para visualizar el
				// saldo
				System.out.println(vivero.toString());
				mostrarPorPantalla("\n\nHasta luego!");
				break;
			}
			}

			// TODO: Completar la condicion
		} while (opcionMenuPrincipal != MenuPrincipal.SALIR);

		teclado.close();
	}

	private static Planta ingresarPlanta() {
		// TODO: Solicitar el ingreso de los datos de una planta y devolver una
		// instancia de Planta. Considerar el uso del metodo
		// ingresarTipoDePlanta(MENU_TIPO_DE_PLANTA)
		int codigo = ingresarNumeroEntero("Ingrese el codigo de la planta");
		TipoDePlanta tipoDePlanta = ingresarTipoDePlanta("Ingrese el tipo de planta");
//		TipoDePlanta tipoDePlanta = ingresarTipoDePlanta(MENU_TIPO_DE_PLANTA);
		String nombre = ingresarString("Ingrese el nombre del tipo de planta");
		Double precio = ingresarDouble("Ingrese el precio");
		int stock = ingresarNumeroEntero("Ingrese el stock de la planta seleccionada");

		Planta planta = new Planta(codigo, tipoDePlanta, nombre, precio, stock);
		return planta;
	}

	private static MenuPrincipal obtenerOpcionDeMenuPrincipal(int numeroIngresado) {
		// TODO: Devolver la opcion seleccionada por el usuario existente en el enum

		MenuPrincipal opcionSeleccionada = null;
		int opcion = numeroIngresado;
		while (opcion < 1 || opcion > MenuPrincipal.values().length && opcion != SALIR) {
			mostrarMenuPrincipal();
			numeroIngresado = teclado.nextInt();
			opcion = numeroIngresado;
		}

		// Salir 99
		if (opcion == 99) {
			opcionSeleccionada = MenuPrincipal.SALIR;
		} else {
			opcionSeleccionada = MenuPrincipal.values()[opcion - 1];
		}

		return opcionSeleccionada;
	}

	private static TipoDePlanta ingresarTipoDePlanta(String mensaje) {
		// TODO: Solicitar el ingreso de la opcion requerida y devolver la opcion del
		// enum
		System.out.println(mensaje);
		for (int i = 0; i < TipoDePlanta.values().length; i++) {
			System.out.println("Ingrese " + (i + 1) + " para " + TipoDePlanta.values()[i]);
		}
		int opcion = 0;
		do {
			System.out.println("ingrese la opcion deseada");
			opcion = teclado.nextInt();

		} while (opcion < 1 || opcion > TipoDePlanta.values().length);
		TipoDePlanta opcionElegida = TipoDePlanta.values()[opcion - 1];

		return opcionElegida;
	}

	private static int ingresarNumeroEntero(String mensaje) {
		// TODO: Mostrar el mensaje y devolver el dato ingresado
		System.out.println(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		// TODO: Mostrar el mensaje y devolver el dato ingresado
		System.out.println(mensaje);
		return teclado.next();
	}

	private static double ingresarDouble(String mensaje) {
		// TODO: Mostrar el mensaje y devolver el dato ingresado
		System.out.println(mensaje);
		return teclado.nextDouble();
	}

	private static void mostrarPlantas(Planta[] plantas) {

		for (int i = 0; i < plantas.length; i++) {
			if (plantas[i] != null) {
				mostrarPorPantalla(plantas[i].toString());
			}
		}
	}

	private static void mostrarMenuPrincipal() {
		mostrarPorPantalla(
				"\n\n1) Agregar planta\n2) Modificar stock de planta\n3) Buscar planta por su codigo\n4) Buscar plantas que en su nombre contienen"
						+ "\n5) Vender planta\n6) Mostrar plantas por tipo de planta\n\n" + SALIR + ") Salir");
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

}
