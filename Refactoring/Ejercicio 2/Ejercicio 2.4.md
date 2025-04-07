### 2.4 Carrito de compras
```java
public class Producto {
    private String nombre;
    private double precio;
    
    public double getPrecio() {
        return this.precio;
    }
}

public class ItemCarrito {
    private Producto producto;
    private int cantidad;
        
    public Producto getProducto() {
        return this.producto;
    }
    
    public int getCantidad() {
        return this.cantidad;
    }

}

public class Carrito {
    private List<ItemCarrito> items;
    
    public double total() {
return this.items.stream()
.mapToDouble(item -> 
item.getProducto().getPrecio() * item.getCantidad())
.sum();
    }
}
```
```
Bad Smell: Envidia de atributos
Refactoring: extract method
```
```java
public class Producto {
    private String nombre;
    private double precio;
    
    public double getPrecio() {
        return this.precio;
    }
}

public class ItemCarrito {
    private Producto producto;
    private int cantidad;
        
    public Producto getProducto() {
        return this.producto;
    }
    
    public int getCantidad() {
        return this.cantidad;
    }

    public double totalItem(){
        return this.producto.getPrecio() * this.cantidad;
    }

}

public class Carrito {
    private List<ItemCarrito> items;
    
    public double total() {
       return this.items.stream().maptoDouble(item -> item.calcularTotal()).sum();
    }
}
```