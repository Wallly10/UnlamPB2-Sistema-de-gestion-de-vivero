package ar.edu.unlam.pb1.dominio;

public enum MenuPrincipal {
	// 1 2 3
	// 0 1 2
	AGREGAR_PLANTA(1), 
	MODIFICAR_STOCK_PLANTA(2), 
	BUSCAR_PLANTA_POR_CODIGO(3), 
	BUSCAR_PLANTAS_QUE_CONTIENEN(4), 
	VENDER_PLANTA(5),
	OBTENER_PLANTAS_DEL_TIPO(6), 
	SALIR(99);
	
	private int numero;
	
	private MenuPrincipal(int numero) {
		this.numero = numero;
	}
	
	
	public int getNumero() {
		return numero;
	}
}
