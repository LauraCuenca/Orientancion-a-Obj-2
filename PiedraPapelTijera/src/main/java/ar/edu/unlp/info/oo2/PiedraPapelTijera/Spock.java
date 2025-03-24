package ar.edu.unlp.info.oo2.PiedraPapelTijera;

public class Spock extends Opcion{

	public Spock() {
		super("Spock");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String jugarContra(Opcion otro) {
		if(otro instanceof Spock) return "Empate";
		if(otro instanceof Tijera || otro instanceof Piedra) return "Gana Spock";
		return "Pierde Spock";
	}

}
