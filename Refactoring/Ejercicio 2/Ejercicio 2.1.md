#### 2.1 Empleados

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
Bad Smeel Generales: Duplicate Code / Nombre poco descriptivo / Variables no encapsuladas / sin constructor
Refactoring Generales: Crear Superclase / Rename Method / Encapsular Variables / Encapsular Constructor (?)
```

```
Bad Smeel: Duplicate Code 
Refactoring: Crear Superclase
```

```java
public abstract class Empleado {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;
}

public class EmpleadoTemporario extends Empleado {
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

public class EmpleadoPlanta extends Empleado {
    public int cantidadHijos = 0;
    // ......
    
    public double sueldo() {
        return this.sueldoBasico 
+ (this.cantidadHijos * 2000)
- (this.sueldoBasico * 0.13);
    }
}

public class EmpleadoPasante extends Empleado {
    // ......
    
    public double sueldo() {
        return this.sueldoBasico - (this.sueldoBasico * 0.13);
    }
}
```
```
Bad Smeel: Sin constructor 
Refactoring: Crear constructor
```

```java
public abstract class Empleado {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;

    public Empleado (String nombre, String apellido, double sueldo ){
    this.nombre = nombre;
    this.apellido = apellido;
    this.sueldoBasico = sueldo; 
    }
}

public class EmpleadoTemporario extends Empleado {
    public double horasTrabajadas = 0;
    public int cantidadHijos = 0;
    // ......

    public EmpleadoTemporario(String nombre, String apellido, double sueldo, double horas, int hijos) {
        super(nombre, apellido, sueldo);
        this.horasTrabajadas = horas;
        this.cantidadHijos = hijos;
    }
    
public double sueldo() {
return this.sueldoBasico
(this.horasTrabajadas * 500) 
(this.cantidadHijos * 1000) 
(this.sueldoBasico * 0.13);
}
}

public class EmpleadoPlanta extends Empleado {
    public int cantidadHijos = 0;
    // ......

        public EmpleadoPlanta(String nombre, String apellido, double sueldo, int hijos) {
        super(nombre, apellido, sueldo);
        this.cantidadHijos = hijos;
    }
    
    public double sueldo() {
        return this.sueldoBasico 
+ (this.cantidadHijos * 2000)
- (this.sueldoBasico * 0.13);
    }
}

public class EmpleadoPasante extends Empleado {
    // ......
        public EmpleadoPasante (String nombre, String apellido, double sueldo) {
        super(nombre, apellido, sueldo); 
    }
    
    public double sueldo() {
        return this.sueldoBasico - (this.sueldoBasico * 0.13);
    }
}
```
```
Bad Smeel Generales: Codigo duplicado
Refactoring Generales: Extract Method
```

```java
public abstract class Empleado {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;

    public Empleado (String nombre, String apellido, double sueldo ){
    this.nombre = nombre;
    this.apellido = apellido;
    this.sueldoBasico = sueldo; 
    }

    protected double descuentoObraSocial(){
        return this.sueldoBasico * 0.13;
    }

    protected double suplementoPorHijos(int cantidadHijos, double montoHijos){
        return cantidadHijos * montoHijos;
    }

    public abstract double sueldo();
}

public class EmpleadoTemporario extends Empleado {
    public double horasTrabajadas = 0;
    public int cantidadHijos = 0;

    public EmpleadoTemporario(String nombre, String apellido, double sueldo, double horas, int hijos) {
        super(nombre, apellido, sueldo);
        this.horasTrabajadas = horas;
        this.cantidadHijos = hijos;
    }

    public double sueldo() {
        return this.sueldoBasico
             + (this.horasTrabajadas * 500)
             + suplementoPorHijos(this.cantidadHijos, 1000)
             + descuentoObraSocial();
    }
}

public class EmpleadoPlanta extends Empleado {
    public int cantidadHijos = 0;

    public EmpleadoPlanta(String nombre, String apellido, double sueldo, int hijos) {
        super(nombre, apellido, sueldo);
        this.cantidadHijos = hijos;
    }

    public double sueldo() {
        return this.sueldoBasico
             + suplementoPorHijos(this.cantidadHijos, 2000)
             - descuentoObraSocial();
    }
}

public class EmpleadoPasante extends Empleado {

    public EmpleadoPasante(String nombre, String apellido, double sueldo) {
        super(nombre, apellido, sueldo);
    }

    public double sueldo() {
        return this.sueldoBasico - descuentoObraSocial();
    }
}
```
```
Bad Smeel Generales: Variables no encapsuladas
Refactoring Generales: Encapsular Variables
```

```java
public abstract class Empleado {
    private String nombre;
    private String apellido;
    private double sueldoBasico = 0;

    public Empleado (String nombre, String apellido, double sueldo ){
    this.nombre = nombre;
    this.apellido = apellido;
    this.sueldoBasico = sueldo; 
    }

    protected double descuentoObraSocial(){
        return this.sueldoBasico * 0.13;
    }

    protected double suplementoPorHijos(int cantidadHijos, double montoHijos){
        return cantidadHijos * montoHijos;
    }

    public abstract double sueldo();
}

public class EmpleadoTemporario extends Empleado {
    private double horasTrabajadas = 0;
    private int cantidadHijos = 0;

    public EmpleadoTemporario(String nombre, String apellido, double sueldo, double horas, int hijos) {
        super(nombre, apellido, sueldo);
        this.horasTrabajadas = horas;
        this.cantidadHijos = hijos;
    }

    public double sueldo() {
        return this.sueldoBasico
             + (this.horasTrabajadas * 500)
             + suplementoPorHijos(this.cantidadHijos, 1000)
             + descuentoObraSocial();
    }
}

public class EmpleadoPlanta extends Empleado {
    private int cantidadHijos = 0;

    public EmpleadoPlanta(String nombre, String apellido, double sueldo, int hijos) {
        super(nombre, apellido, sueldo);
        this.cantidadHijos = hijos;
    }

    public double sueldo() {
        return this.sueldoBasico
             + suplementoPorHijos(this.cantidadHijos, 2000)
             - descuentoObraSocial();
    }
}

public class EmpleadoPasante extends Empleado {

    public EmpleadoPasante(String nombre, String apellido, double sueldo) {
        super(nombre, apellido, sueldo);
    }

    public double sueldo() {
        return this.sueldoBasico - descuentoObraSocial();
    }
}
```