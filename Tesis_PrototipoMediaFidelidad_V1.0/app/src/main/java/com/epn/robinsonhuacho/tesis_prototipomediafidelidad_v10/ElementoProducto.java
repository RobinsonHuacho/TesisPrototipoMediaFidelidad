package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

/**
 * Entidad ElementoCategoriaProducto
 */
public class ElementoProducto {
    private String IdProducto;
    private String IdCategoriaProducto;
    private String Nombres;
    private String Descripcion;
    private String Precio;
    private String Imagen;

    public ElementoProducto() {
    }

    public ElementoProducto(String idProducto, String idCategoriaProducto, String nombres, String descripcion, String precio, String imagen){
        IdProducto = idProducto;
        IdCategoriaProducto= idCategoriaProducto;
        Nombres = nombres;
        Descripcion=descripcion;
        Precio=precio;
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

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producuto {" +
                "ID : '" + IdProducto + '\'' +
                ", Nombre : '" + Nombres + '\'' +
                ", Precio : '" + Precio + '\'' +

                '}';
    }
}
