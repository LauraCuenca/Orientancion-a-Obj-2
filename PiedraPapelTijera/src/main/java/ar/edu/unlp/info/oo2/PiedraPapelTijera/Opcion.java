package ar.edu.unlp.info.oo2.PiedraPapelTijera;

abstract class Opcion {
	protected String nombre;
	
	public Opcion(String n) {
		this.nombre= n;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public abstract String jugarContra(Opcion otro);
}
