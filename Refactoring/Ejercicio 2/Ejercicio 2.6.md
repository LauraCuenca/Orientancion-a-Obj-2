### 2.6 Películas
```java
public class Usuario {
    String tipoSubscripcion;
    // ...

    public void setTipoSubscripcion(String unTipo) {
   	 this.tipoSubscripcion = unTipo;
    }
    
    public double calcularCostoPelicula(Pelicula pelicula) {
   	 double costo = 0;
   	 if (tipoSubscripcion=="Basico") {
   		 costo = pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno();
   	 }
   	 else if (tipoSubscripcion== "Familia") {
   		 costo = (pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno()) * 0.90;
   	 }
   	 else if (tipoSubscripcion=="Plus") {
   		 costo = pelicula.getCosto();
   	 }
   	 else if (tipoSubscripcion=="Premium") {
   		 costo = pelicula.getCosto() * 0.75;
   	 }
   	 return costo;
    }
}

public class Pelicula {
    LocalDate fechaEstreno;
    // ...

    public double getCosto() {
   	 return this.costo;
    }
    
    public double calcularCargoExtraPorEstreno(){
	// Si la Película se estrenó 30 días antes de la fecha actual, retorna un cargo de 0$, caso contrario, retorna un cargo extra de 300$
   	return (ChronoUnit.DAYS.between(this.fechaEstreno, LocalDate.now()) ) > 30 ? 0 : 300;
    }
}
```
```
Bad Smells generales: Long Method / Reinventar la rueda / Envidia de atributos
Refactoring generales: Reemplazar con pipelines /Move Method
```

```
Bad Smell: Reinventar la rueda / Long Method
Refactoring: Replace Conditional with Strategy
```
```java
public class Usuario {
    String tipoSubscripcion;
    // ...

    public void setTipoSubscripcion(String unTipo) {
   	 this.tipoSubscripcion = unTipo;
    }
    
    private static final Map<String, Function<Pelicula, Double>> calculadores = Map.of(
        "Basico", p -> p.getCosto() + p.calcularCargoExtraPorEstreno(),
        "Familia", p -> (p.getCosto() + p.calcularCargoExtraPorEstreno()) * 0.90,
        "Plus", p -> p.getCosto(),
        "Premium", p -> p.getCosto() * 0.75
    );

    public double calcularCostoPelicula(Pelicula pelicula) {
        return calculadores.getOrDefault(tipoSubscripcion, p -> p.getCosto()).apply(pelicula);
    }
}

public class Pelicula {
    LocalDate fechaEstreno;
    // ...

    public double getCosto() {
   	 return this.costo;
    }
    
    public double calcularCargoExtraPorEstreno(){
	// Si la Película se estrenó 30 días antes de la fecha actual, retorna un cargo de 0$, caso contrario, retorna un cargo extra de 300$
   	return (ChronoUnit.DAYS.between(this.fechaEstreno, LocalDate.now()) ) > 30 ? 0 : 300;
    }
}
```
```
Bad Smell: codigo duplicado
Refactoring: Reemplazar condicional con polimorfismo
```
```java
public class Cliente {
    private TipoSubscripcion tipoSubscripcion;

    public Cliente(TipoSubscripcion tipoSubscripcion) {
        this.tipoSubscripcion = tipoSubscripcion;
    }

    public double calcularCostoPelicula(Pelicula pelicula) {
        return tipoSubscripcion.calcularCosto(pelicula);
    }
}

public interface TipoSubscripcion {
    double calcularCosto(Pelicula pelicula);
}

public class Basico implements TipoSubscripcion {
    public double calcularCosto(Pelicula pelicula) {
        return pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno();
    }
}

public class Familia implements TipoSubscripcion {
    public double calcularCosto(Pelicula pelicula) {
        return (pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno()) * 0.90;
    }
}

public class Plus implements TipoSubscripcion {
    public double calcularCosto(Pelicula pelicula) {
        return pelicula.getCosto();
    }
}

public class Premium implements TipoSubscripcion {
    public double calcularCosto(Pelicula pelicula) {
        return pelicula.getCosto() * 0.75;
    }
}

```