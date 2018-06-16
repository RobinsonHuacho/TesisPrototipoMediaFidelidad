package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

/**
 * Entidad ElementoCategoriaProducto
 */
public class ElementoProducto {
    private String IdProducto;
    private String IdCategoriaProducto;
    private String Nombres;
    private String Descripcion;
    private int Cantidad;
    private double Precio;
    private double Total;
    private int Imagen;


    public ElementoProducto(String idProducto, String idCategoriaProducto, String nombres, String descripcion, double precio, int cantidad, int imagen){
        IdProducto = idProducto;
        IdCategoriaProducto= idCategoriaProducto;
        Nombres = nombres;
        Descripcion=descripcion;
        Precio=precio;
        Cantidad=cantidad;
        Imagen=imagen;
    }

    public String getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(String idProducto) {
        IdProducto = idProducto;
    }

    public String getIdCategoriaProducto() {
        return IdCategoriaProducto;
    }

    public void setIdCategoriaProducto(String idCategoriaProducto) {
        IdCategoriaProducto = idCategoriaProducto;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public double getTotal() {
        return Math.round(Cantidad*Precio*100d)/100d;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producuto {" +
                "ID : '" + IdProducto + '\'' +
                ", Nombres : '" + Nombres + '\'' +
                ", Precio : '" + Precio + '\'' +
                ", Total : '" + Total + '\'' +
                '}';
    }
}
