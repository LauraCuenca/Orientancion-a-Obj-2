```
#### 2.1 Empleados
```
```java
public class EmpleadoTemporario {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;
    public double horasTrabajadas = 0;
    public int cantidadHijos = 0;
    // ......
    
public double sueldo() {
return this.sueldoBasico
(this.horasTrabajadas * 500) 
(this.cantidadHijos * 1000) 
(this.sueldoBasico * 0.13);
}
}

public class EmpleadoPlanta {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;
    public int cantidadHijos = 0;
    // ......
    
    public double sueldo() {
        return this.sueldoBasico 
+ (this.cantidadHijos * 2000)
- (this.sueldoBasico * 0.13);
    }
}

public class EmpleadoPasante {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;
    // ......
    
    public double sueldo() {
        return this.sueldoBasico - (this.sueldoBasico * 0.13);
    }
}
```
```
Bad Smeel Generales: Duplicate Code / Nombre poco descriptivo / 
Refactoring Generales: 
