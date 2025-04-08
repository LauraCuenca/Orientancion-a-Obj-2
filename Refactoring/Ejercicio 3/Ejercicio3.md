### Ejercicio 3 
Dado el siguiente código implementado en la clase Document y que calcula algunas estadísticas del mismo:
```java
public class Document {
    List<String> words;
  
    public long characterCount() {
 	long count = this.words
                 .stream()
                 .mapToLong(w -> w.length())
                 .sum();
    	return count;
	}
    public long calculateAvg() {
    	long avgLength = this.words
                         .stream()
                         .mapToLong(w -> w.length())
                         .sum() / this.words.size();
 	return avgLength;
	}
// Resto del código que no importa
}
```

Tareas:
1. Enumere los code smell y que refactorings utilizará para solucionarlos.
2. Aplique los refactorings encontrados, mostrando el código refactorizado luego de aplicar cada uno.
3. Analice el código original y detecte si existe un problema al calcular las estadísticas. Explique cuál es el error y en qué casos se da ¿El error identificado sigue presente luego de realizar los refactorings? En caso de que no esté presente, ¿en qué momento se resolvió? De acuerdo a lo visto en la teoría, ¿podemos considerar esto un refactoring?

```
Bad Smells generales: Nombre poco descriptivo / Codigo duplicado
Refactoring generales: Renombrar variable / Extraer metodo 
```
```java
public class Document {
    List<String> words;
  
    public long characterCount() {
 	long count = this.words
                 .stream()
                 .mapToLong(w -> w.length())
                 .sum();
    	return count;
	}
    public long calculateAvg() {
        return characterCount() / this.words.size();
    }
// Resto del código que no importa
}
```
3. Puede haber un problema si la lista esta vacia y se divide por 0. En este caso de refactorizacion sigue estando el riesgo.
Y si se realizo un refactoring, al extraer el codigo duplicado y renombrar una variable.

