package ar.edu.unlp.info.oo2.RedSocial;

public class Main {
	public static void main(String[] args) {
		
		Twitter twitter= new Twitter();
		
		Usuario usu1= new Usuario("Laura4548");
		Usuario usu2= new Usuario("Akus7889");
		
		twitter.agregarUsuario(usu1);
		twitter.agregarUsuario(usu2);
		
		Tweet tw1= new Tweet ("Holisss");
		Tweet tw2= new Tweet ("Que humedad que hay");
		
		usu1.getTweets().add(tw1);
		usu1.getTweets().add(tw2);
		
		usu2.hacerRt(tw2);
		
		System.out.println("Tweets de " + usu1.getScreenName()+ " después de hacer el retweet: " + usu1.getTweets().size());
		
		System.out.println("Tweets de " + usu2.getScreenName() + " después de hacer el retweet: " + usu2.getTweets().size());
    }
}
