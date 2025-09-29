package ar.edu.unlam.pb1.dominio;

import java.util.Arrays;
import java.util.Random;

public class Vivero {

	private static double saldo = 0;
	private String nombre;
	private int cupoMacetas;
	private Planta[] plantas;
	private String contrasenia;

	public Vivero(String nombre, int cupoMacetas) {
		// TODO: Completar el constructor. La contrasenia debe generarse y asignarse al
		// vivero en este constructor
		this.nombre = nombre;
		this.cupoMacetas = cupoMacetas;
		this.plantas = new Planta[cupoMacetas];
		this.contrasenia = generarContrasenia();
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCupoMacetas() {
		return cupoMacetas;
	}

	public void setCupoMacetas(int cupoMacetas) {
		this.cupoMacetas = cupoMacetas;
	}

	public static double obtenerSaldo() {
		return saldo;
	}

	public boolean iniciarSesion(String contrasenia) {
		boolean sesionIniciada = false;
		if (this.contrasenia.equals(contrasenia)) {
			sesionIniciada = true;
		}
		return sesionIniciada;
	}

	public boolean agregarPlanta(Planta planta) {
		// TODO: Agregar la planta al array solo si no existe otra con el mismo codigo
		// en el array de plantas. Considerar usar el metodo
		// buscarPlantaPorCodigo(codigo)
		boolean agregado = false;
//		int indice =0;
//		
//		while (indice < this.plantas.length && !agregado ) {
//			if(this.plantas[indice] == null) {
//				this.plantas[indice] = planta;
//				agregado = true;
//			}
//			indice ++;		
//		}
		for (int i = 0; i < plantas.length; i++) {
			if (this.plantas[i] == null) {
				this.plantas[i] = planta;
				agregado = true;
				break;
			}
		}
		return agregado;
		
	}

	public boolean venderPlanta(int codigo, int cantidadAVender) {
		// TODO: Para vender una planta, primero debemos revisar que exista (buscando
		// por codigo por ejemplo) y luego verificar que tenga stock para satisfacer la
		// cantidad a vender. Si es viable la venta, se debera modificar el stock de la
		// planta en el array de plantas (considerar el metodo
		// modificarStockDePlantaPorCodigo()) para
		// disminuir el mismo, y se deberá
		// acumular el precio final de la venta al saldo del vivero. Ejemplo: Precio
		// final de planta $10, cantidad de plantas en la venta 10 unidades, entonces se
		// acumulan $100 al saldo del vivero

		Planta planta = buscarPlantaPorCodigo(codigo);
		boolean venta = false;
		if (planta != null && planta.getStock() >= cantidadAVender) {
			planta.setStock(planta.getStock() - cantidadAVender);
			saldo += (planta.getPrecio() * cantidadAVender);
			venta = true;
		}

		return venta;
	}

	public Planta buscarPlantaPorCodigo(int codigo) {
		// TODO: Buscar la planta en el array de plantas utilizando el codigo de la
		// planta. En caso de no existir, devolver null.
		Planta planta = null;
		for (int i = 0; i < plantas.length; i++) {
			if (this.plantas[i] != null) {
				if (this.plantas[i].getCodigo() == codigo) {
					planta = this.plantas[i];
				}
			}
		}

		return planta;
	}

	public Planta[] buscarPlantasQueContienen(String texto) {
		// TODO: Generar un nuevo array con las plantas que en su nombre, contengan el
		// texto suministrado. El array no debe poseer espacios, es decir, las plantas
		// deben ingresar ordenadamente (no permitir posiciones en null entre las
		// plantas ingresadas).
		
		Planta [] buscarPlanta = new Planta[plantas.length];
		int index =0;
		for (int i = 0; i < plantas.length; i++) {
			if (this.plantas[i] != null ) {
				if (this.plantas[i].getNombre().contains(texto)) {
					buscarPlanta[index]= this.plantas[i];
					index++;
				}
			}
		}
		return buscarPlanta;
	}

	public boolean modificarStockDePlantaPorCodigo(int codigo, int stock) {
		// TODO: Iterar el array de plantas y en caso de existir alguna y que coincida
		// con el codigo suministrado, actualizar el stock de la misma

		boolean modificado = false;
		for (int i = 0; i < plantas.length; i++) {
			if (this.plantas[i] != null) {
				if (this.plantas[i].getCodigo() == codigo) {
					this.plantas[i].setStock(stock);
					modificado = true;
				}
			}
		}

		return modificado;
	}

	public Planta[] obtenerPlantasDeTipoOrdenadasPorNombreAscendende(TipoDePlanta tipoDePlanta) {
		// TODO: Generar un nuevo array de plantas que coincidan con el tipo de planta
		// suministrado. Antes de devolverlo, se debera ordenar por nombre ascendente.
		// Considerar el metodo ordenarPlantasPorNombreAscendente(array)

		Planta[] devolverPlantaPorTipo = new Planta [plantas.length];
		int index = 0;
		for (int i = 0; i < plantas.length; i++) {
			if (this.plantas[i] != null) {
				if (this.plantas[i].getTipoDeplanta() == tipoDePlanta) {
					devolverPlantaPorTipo[index] = this.plantas[i];
					index ++;
				}
			}
		}
		
		ordenarPlantasPorNombreAscendente(devolverPlantaPorTipo);
		return devolverPlantaPorTipo;
	}

	private String generarContrasenia() {
		// TODO: Generar un contrasenia aleatoria y devolverla. La misma debe poseer:
		// 4 caracteres (en el orden que desee) los cuales deben ser:
		// 1 numero.
		// 1 letra mayuscula.
		// 2 letras minusculas.
		// Considerar el metodo obtenerCaracterAleatorio(1,10).

		char numero = obtenerCaracterAleatorio('0', '9');
		char minuscula1 = obtenerCaracterAleatorio('a', 'z');
		char minuscula2 = obtenerCaracterAleatorio('a', 'z');
		char mayuscula = obtenerCaracterAleatorio('A', 'Z');
		String contraseniaGenerada = "" + numero + minuscula1 + minuscula2 + mayuscula;
		return contraseniaGenerada;
	}

	private char obtenerCaracterAleatorio(int posicionInicial, int posicionFinal) {
		// TODO: Generar un caracter aleatorio considerando las posiciones de los
		// caracteres en la tabla ASCII
		Random random = new Random();
		char caracter = (char) (random.nextInt(posicionFinal - posicionInicial + 1) + posicionInicial);
//		char caracter = (char) (Math.random()*(posicionFinal - posicionInicial + 1) + posicionInicial);
		return caracter;
	}

	private Planta[] ordenarPlantasPorNombreAscendente(Planta[] plantas) {
		// TODO: Ordenar las plantas en el array suministrado por nombre de manera
		// ascendente y devolver el array

		Planta aux = null;

		for (int i = 0; i < plantas.length; i++) {
			for (int j = 0; j < plantas.length - 1; j++) {
				if (this.plantas[j] != null && this.plantas[j + 1] != null) {
					if (this.plantas[j].getNombre().compareToIgnoreCase(this.plantas[j + 1].getNombre()) > 0) {
						aux = this.plantas[j];
						this.plantas[j] = this.plantas[j + 1];
						this.plantas[j + 1] = aux;
					}
				}
			}
		}

		return this.plantas;
	}

	@Override
	public String toString() {
		return "Vivero [nombre=" + nombre + ", cupoMacetas=" + cupoMacetas + ", plantas=" + Arrays.toString(plantas)
				+ " , saldo=" + obtenerSaldo() + "]";
	}

}
