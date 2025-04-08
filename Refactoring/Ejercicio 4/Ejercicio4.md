### Ejercicio 4 
Se tiene el siguiente modelo de un sistema de pedidos y la correspondiente implementación.

```java
01: public class Pedido {
02:  private Cliente cliente;
03:  private List<Producto> productos;
04:  private String formaPago;
05:  public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
06:     if (!"efectivo".equals(formaPago)
07:        && !"6 cuotas".equals(formaPago)
08:        && !"12 cuotas".equals(formaPago)) {
09:          throw new Error("Forma de pago incorrecta");
10:    }
11:    this.cliente = cliente;
12:    this.productos = productos;
13:    this.formaPago = formaPago;
14:   }
15:   public double getCostoTotal() {
16:     double costoProductos = 0;
17:     for (Producto producto : this.productos) {
18:       costoProductos += producto.getPrecio();
19:     }
20:     double extraFormaPago = 0;
21:     if ("efectivo".equals(this.formaPago)) {
22:       extraFormaPago = 0;
23:     } else if ("6 cuotas".equals(this.formaPago)) {
24:       extraFormaPago = costoProductos * 0.2;
25:     } else if ("12 cuotas".equals(this.formaPago)) {
26:       extraFormaPago = costoProductos * 0.5;
27:     }
28:     int añosDesdeFechaAlta = Period.between(this.cliente.getFechaAlta(), LocalDate.now()).getYears();
29:     // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
30:     if (añosDesdeFechaAlta > 5) {
31:       return (costoProductos + extraFormaPago) * 0.9;
32:     }
33:     return costoProductos + extraFormaPago;
34:   }
35: }
36: public class Cliente {
37:   private LocalDate fechaAlta;
38:   public LocalDate getFechaAlta() {
39:     return this.fechaAlta;
40:   }
41: }
42: public class Producto {
43:   private double precio;
44:   public double getPrecio() {
45:     return this.precio;
46:   }
47: }
```
Tareas:
1. Dado el código anterior, aplique únicamente los siguientes refactoring:
  - Replace Loop with Pipeline (líneas 16 a 19)
  - Replace Conditional with Polymorphism (líneas 21 a 27)
  - Extract method y move method (línea 28)
  - Extract method y replace temp with query (líneas 28 a 33)
Realice el diagrama de clases del código refactorizado

```
 - Replace Loop with Pipeline (líneas 16 a 19)
```
```java
01: public class Pedido {
02:  private Cliente cliente;
03:  private List<Producto> productos;
04:  private String formaPago;
05:  public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
06:     if (!"efectivo".equals(formaPago)
07:        && !"6 cuotas".equals(formaPago)
08:        && !"12 cuotas".equals(formaPago)) {
09:          throw new Error("Forma de pago incorrecta");
10:    }
11:    this.cliente = cliente;
12:    this.productos = productos;
13:    this.formaPago = formaPago;
14:   }
15:   public double getCostoTotal() {
16:     double costoProductos = this.productos.stream()
17:                              .mapToDouble(Producto::getPrecio())
18:                              .sum();
19:     }
20:     double extraFormaPago = 0;
21:     if ("efectivo".equals(this.formaPago)) {
22:       extraFormaPago = 0;
23:     } else if ("6 cuotas".equals(this.formaPago)) {
24:       extraFormaPago = costoProductos * 0.2;
25:     } else if ("12 cuotas".equals(this.formaPago)) {
26:       extraFormaPago = costoProductos * 0.5;
27:     }
28:     int añosDesdeFechaAlta = Period.between(this.cliente.getFechaAlta(), LocalDate.now()).getYears();
29:     // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
30:     if (añosDesdeFechaAlta > 5) {
31:       return (costoProductos + extraFormaPago) * 0.9;
32:     }
33:     return costoProductos + extraFormaPago;
34:   }
35: }
36: public class Cliente {
37:   private LocalDate fechaAlta;
38:   public LocalDate getFechaAlta() {
39:     return this.fechaAlta;
40:   }
41: }
42: public class Producto {
43:   private double precio;
44:   public double getPrecio() {
45:     return this.precio;
46:   }
47: }
```
```
- Replace Conditional with Polymorphism (líneas 21 a 27)
```
```java
interface EstrategiaPago {
    double calcularExtra(double costoBase);
}

class PagoEfectivo implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return 0;
    }
}

class Pago6Cuotas implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return costoBase * 0.2;
    }
}

class Pago12Cuotas implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return costoBase * 0.5;
    }
}

01: public class Pedido {
02:  private Cliente cliente;
03:  private List<Producto> productos;
04:  private String formaPago;
05:  public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
06:     if (!"efectivo".equals(formaPago)
07:        && !"6 cuotas".equals(formaPago)
08:        && !"12 cuotas".equals(formaPago)) {
09:          throw new Error("Forma de pago incorrecta");
10:    }
11:    this.cliente = cliente;
12:    this.productos = productos;
13:    this.formaPago = formaPago;
14:   }
15:   public double getCostoTotal() {
16:     double costoProductos = this.productos.stream()
17:                              .mapToDouble(Producto::getPrecio())
18:                              .sum();
19:     }
20:     double extraFormaPago = 0;
21:       EstrategiaPago estrategia = switch (this.formaPago) {
22:        case "efectivo" -> new PagoEfectivo();
23:         case "6 cuotas" -> new Pago6Cuotas();
24:          case "12 cuotas" -> new Pago12Cuotas();
25:          default -> throw new Error("Forma de pago no válida");
26:          };
27:     extraFormaPago = estrategia.calcularExtra(costoProductos); 
28:     int añosDesdeFechaAlta = Period.between(this.cliente.getFechaAlta(), LocalDate.now()).getYears();
29:     // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
30:     if (añosDesdeFechaAlta > 5) {
31:       return (costoProductos + extraFormaPago) * 0.9;
32:     }
33:     return costoProductos + extraFormaPago;
34:   }
35: }
36: public class Cliente {
37:   private LocalDate fechaAlta;
38:   public LocalDate getFechaAlta() {
39:     return this.fechaAlta;
40:   }
41: }
42: public class Producto {
43:   private double precio;
44:   public double getPrecio() {
45:     return this.precio;
46:   }
47: }
```
```
  - Extract method y move method (línea 28)
```
```java
interface EstrategiaPago {
    double calcularExtra(double costoBase);
}

class PagoEfectivo implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return 0;
    }
}

class Pago6Cuotas implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return costoBase * 0.2;
    }
}

class Pago12Cuotas implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return costoBase * 0.5;
    }
}

01: public class Pedido {
02:  private Cliente cliente;
03:  private List<Producto> productos;
04:  private String formaPago;
05:  public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
06:     if (!"efectivo".equals(formaPago)
07:        && !"6 cuotas".equals(formaPago)
08:        && !"12 cuotas".equals(formaPago)) {
09:          throw new Error("Forma de pago incorrecta");
10:    }
11:    this.cliente = cliente;
12:    this.productos = productos;
13:    this.formaPago = formaPago;
14:   }
15:   public double getCostoTotal() {
16:     double costoProductos = this.productos.stream()
17:                              .mapToDouble(Producto::getPrecio())
18:                              .sum();
19:     }
20:     double extraFormaPago = 0;
21:       EstrategiaPago estrategia = switch (this.formaPago) {
22:        case "efectivo" -> new PagoEfectivo();
23:         case "6 cuotas" -> new Pago6Cuotas();
24:          case "12 cuotas" -> new Pago12Cuotas();
25:          default -> throw new Error("Forma de pago no válida");
26:          };
27:     extraFormaPago = estrategia.calcularExtra(costoProductos); 
28:     int añosDesdeFechaAlta = this.cliente.añosDesdeAlta();
29:     // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
30:     if (añosDesdeFechaAlta > 5) {
31:       return (costoProductos + extraFormaPago) * 0.9;
32:     }
33:     return costoProductos + extraFormaPago;
34:   }
35: }
36: public class Cliente {
37:   private LocalDate fechaAlta;
38:   public LocalDate getFechaAlta() {
39:     return this.fechaAlta;
40:   }
     public int añosDesdeFechaAlta(){
      return Period.between(this.fechaAlta,LocalDate.now()).getYears();
     }
41: }
42: public class Producto {
43:   private double precio;
44:   public double getPrecio() {
45:     return this.precio;
46:   }
47: }
```
```
 - Extract method y replace temp with query (líneas 28 a 33)
```
```java
interface EstrategiaPago {
    double calcularExtra(double costoBase);
}

class PagoEfectivo implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return 0;
    }
}

class Pago6Cuotas implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return costoBase * 0.2;
    }
}

class Pago12Cuotas implements EstrategiaPago {
    public double calcularExtra(double costoBase) {
        return costoBase * 0.5;
    }
}

01: public class Pedido {
02:  private Cliente cliente;
03:  private List<Producto> productos;
04:  private String formaPago;
05:  public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
06:     if (!"efectivo".equals(formaPago)
07:        && !"6 cuotas".equals(formaPago)
08:        && !"12 cuotas".equals(formaPago)) {
09:          throw new Error("Forma de pago incorrecta");
10:    }
11:    this.cliente = cliente;
12:    this.productos = productos;
13:    this.formaPago = formaPago;
14:   }
15:   public double getCostoTotal() {
16:     double costoProductos = this.productos.stream()
17:                              .mapToDouble(Producto::getPrecio())
18:                              .sum();
19:     }
20:     double extraFormaPago = 0;
21:       EstrategiaPago estrategia = switch (this.formaPago) {
22:        case "efectivo" -> new PagoEfectivo();
23:         case "6 cuotas" -> new Pago6Cuotas();
24:          case "12 cuotas" -> new Pago12Cuotas();
25:          default -> throw new Error("Forma de pago no válida");
26:          };
27:     extraFormaPago = estrategia.calcularExtra(costoProductos); 
       if (this.cliente.tieneDescuentoAntiguedad()) {
           return (costoProductos + extraFormaPago) * 0.9;
        }
33:     return costoProductos + extraFormaPago;
34:   }
35: }
36: public class Cliente {
37:   private LocalDate fechaAlta;
38:   public LocalDate getFechaAlta() {
39:     return this.fechaAlta;
40:   }
     public int añosDesdeFechaAlta(){
      return Period.between(this.fechaAlta,LocalDate.now()).getYears();
     }
     public boolean tieneDescuentoAntiguedad() {
      return añosDesdeFechaAlta() > 5;
     }
41: }
42: public class Producto {
43:   private double precio;
44:   public double getPrecio() {
45:     return this.precio;
46:   }
47: }
```