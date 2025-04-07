### 2.2 Juego 
```java
public class Juego {
    // ......
    public void incrementar(Jugador j) {
        j.puntuacion = j.puntuacion + 100;
    }
    public void decrementar(Jugador j) {
        j.puntuacion = j.puntuacion - 50;
    }

public class Jugador {
    public String nombre;
    public String apellido;
    public int puntuacion = 0;
}


}
```

```
Bad Smells generales: Envidia de atributos / Atributos Publicos / Sin constructor
Refactoring generales: Extract Method / Encapsular atributos / Crear Constructor
```
```
Bad Smells : Envidia de atributos
Refactoring: Extract Method, el incremento de decremento de puntuacion le corresponderia hacer a cada jugador
```
```java
public class Juego {
    // ......
    public void incrementar(Jugador j) {
        j.incrementarPuntuacion();
    }
    public void decrementar(Jugador j) {
        j.decrementarPuntuacion();
    }

public class Jugador {
    public String nombre;
    public String apellido;
    public int puntuacion = 0;
}
      public void incrementarPuntuacion(Jugador j) {
        this.puntuacion += 100;
    }
    public void decrementarPuntuacion(Jugador j) {
        this.puntuacion -= 50;
    }

}
```
```
Bad Smells : Atributos Publicos
Refactoring: Encapsular atributos
```
```java
public class Juego {
    // ......
    public void incrementar(Jugador j) {
        j.incrementarPuntuacion();
    }
    public void decrementar(Jugador j) {
        j.decrementarPuntuacion();
    }

public class Jugador {
    private String nombre;
    private String apellido;
    private int puntuacion = 0;
}
      public void incrementarPuntuacion(Jugador j) {
        this.puntuacion += 100;
    }
    public void decrementarPuntuacion(Jugador j) {
        this.puntuacion -= 50;
    }

}
```
```
Bad Smells : Sin constructor
Refactoring: Crear constructor
```
```java
public class Juego {
    // ......
    public void incrementar(Jugador j) {
        j.incrementarPuntuacion();
    }
    public void decrementar(Jugador j) {
        j.decrementarPuntuacion();
    }

public class Jugador {
    private String nombre;
    private String apellido;
    private int puntuacion;

    public Jugador( String nombre, String apellido){
      this.nombre= nombre;
      this.apellido = apellido;
      this.puntuacion =0;
    }

      public void incrementarPuntuacion(Jugador j) {
        this.puntuacion += 100;
    }
    public void decrementarPuntuacion(Jugador j) {
        this.puntuacion -= 50;
    }
}

}
```