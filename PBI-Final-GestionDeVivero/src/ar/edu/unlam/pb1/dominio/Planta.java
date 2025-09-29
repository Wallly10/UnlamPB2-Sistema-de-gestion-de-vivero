package ar.edu.unlam.pb1.dominio;

public class Planta {

	private final double GANANCIA_HIERBA_MATA = 1.2;
	private final double GANANCIA_ARBUSTO = 1.6;
	private final double GANANCIA_ARBOL = 2.3;
	private int codigo;
	private TipoDePlanta tipoDeplanta;
	private String nombre;
	private double precio;
	private int stock;

	public Planta(int codigo, TipoDePlanta tipoDePlanta, String nombre, double precio, int stock) {
		// TODO: Completar el constructor
		this.codigo = codigo;
		this.tipoDeplanta = tipoDePlanta;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public TipoDePlanta getTipoDeplanta() {
		return tipoDeplanta;
	}

	public void setTipoDeplanta(TipoDePlanta tipoDeplanta) {
		this.tipoDeplanta = tipoDeplanta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return obtenerPrecioFinal();
//		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double obtenerPrecioFinal() {
		// TODO: Calcular y devolver el precio final de la planta. Para ello, se debera
		// revisar el TipoDePlanta considerando:
		// Hierba o Mata: se incrementa un 20% el precio base.
		// Arbusto: se incrementa un 60% el precio base.
		// Arbol: se incrementa un 130% el precio base.
		// Usar las constantes

		double precioFinal = this.precio;
		switch(this.tipoDeplanta) {
		case HIERBA:
			precioFinal *= GANANCIA_HIERBA_MATA;
			break;
		case MATA:
			precioFinal *= GANANCIA_HIERBA_MATA;
			break;
		case ARBOL:
			precioFinal *= GANANCIA_ARBOL;
			break;
		case ARBUSTO:
			precioFinal *= GANANCIA_ARBUSTO;
			break;
		}
		
		return precioFinal;
	}

	@Override
	public String toString() {
		// TODO: Mostrar la informacion actual de la planta incluyendo el precio final.
		// Considerar el metodo: obtenerPrecioFinal()
		return "Planta = [codigo = " + codigo + ", Tipo De Planta = " + tipoDeplanta + ", nombre = " + nombre
				+ ", precio = " + precio + " , stock = " + stock + ", Precio Final = " + obtenerPrecioFinal() + "]";
	}
}
