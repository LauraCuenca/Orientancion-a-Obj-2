package ar.edu.unlp.info.oo2.PiedraPapelTijera;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JuegoTest {
	
	@Test
	public void testJugar() {
		Opcion piedra = new Piedra();
		Opcion papel = new Papel();
		Opcion tijera = new Tijera();
		Opcion lagarto = new Lagarto();
		Opcion spock = new Spock();
		
		//Piedra
        assertEquals("Empate", Juego.jugar(piedra, piedra));
        assertEquals("Gana Piedra", Juego.jugar(piedra, lagarto));
        assertEquals("Pierde Piedra", Juego.jugar(piedra, spock));
        
		//Papel
        assertEquals("Empate", Juego.jugar(papel, papel));
        assertEquals("Gana Papel", Juego.jugar(papel, spock));
        assertEquals("Pierde Papel", Juego.jugar(papel, lagarto));
        
		//Tijera
        assertEquals("Empate", Juego.jugar(tijera, tijera));
        assertEquals("Gana Tijera", Juego.jugar(tijera, papel));
        assertEquals("Pierde Tijera", Juego.jugar(tijera, piedra));
        
		//Lagarto
        assertEquals("Empate", Juego.jugar(lagarto, lagarto));
        assertEquals("Gana Lagarto", Juego.jugar(lagarto, papel));
        assertEquals("Pierde Lagarto", Juego.jugar(lagarto, piedra));
        
		//Spock
        assertEquals("Empate", Juego.jugar(spock, spock));
        assertEquals("Gana Spock", Juego.jugar(spock, piedra));
        assertEquals("Pierde Spock", Juego.jugar(spock, papel));
	}

}
