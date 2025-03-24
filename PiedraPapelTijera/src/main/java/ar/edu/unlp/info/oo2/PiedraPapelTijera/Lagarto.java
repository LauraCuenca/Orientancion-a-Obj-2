package ar.edu.unlp.info.oo2.PiedraPapelTijera;

public class Lagarto extends Opcion{

	public Lagarto() {
		super("Lagarto");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String jugarContra(Opcion otro) {
		if(otro instanceof Lagarto) return "Empate";
		if(otro instanceof Papel || otro instanceof Spock) return "Gana Lagarto";
		return "Pierde Lagarto";
	}
	

}
