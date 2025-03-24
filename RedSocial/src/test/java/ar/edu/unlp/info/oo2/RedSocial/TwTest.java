package ar.edu.unlp.info.oo2.RedSocial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwTest {
	
	Twitter twitter;
	
	@BeforeEach 
	void setUp() throws Exception {
		twitter = new Twitter();
	}
	
	@Test
	public void testAgregarUsuario() {
		Usuario usuario = new Usuario("Laura4548");
		assertEquals("Laura4548", usuario.getScreenName(), "El nombre del usuario debe coincidir.");	
	}
	
	public void testCrearTw() {
		Tweet tw = new Tweet("Holis, estoy estudiando");
		
		assertTrue(tw.getMensaje().length()<= 280);
		assertTrue(tw.getMensaje().length()>= 1);
	}
	
	public void testRetweet() {
        Usuario usuario = new Usuario("Laura4548");
        Tweet tweetOriginal = new Tweet("Este es el tweet original.");
		
        usuario.getTweets().add(tweetOriginal);
        usuario.hacerRt(tweetOriginal);
        
        assertEquals(2, usuario.getTweets().size(), "El usuario debe tener 2 tweets después del retweet.");
	}
	
	public void testEliminarUsuarioyTw() {
		Usuario usuario = new Usuario("Laura");
        Tweet tweet1 = new Tweet("Tweet 1");
        Tweet tweet2 = new Tweet("Tweet 2");
        usuario.getTweets().add(tweet1);
        usuario.getTweets().add(tweet2);
        
        assertEquals(2, usuario.getTweets().size(), "El usuario debe tener 2 tweets.");
        usuario.deleteTweets();
        assertNull(usuario.getTweets(), "La lista de tweets del usuario debe ser nula después de eliminar los tweets.");
	}

}
