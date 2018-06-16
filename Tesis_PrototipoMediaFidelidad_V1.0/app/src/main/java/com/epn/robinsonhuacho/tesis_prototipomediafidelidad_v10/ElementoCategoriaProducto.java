package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

/**
 * Created by Robinson Huacho on 16/06/2018.
 */

public class ElementoCategoriaProducto {
    //Componentes Categorías Productos
    private String IdCategoriaProducto;
    private String NombreCategoriaProducto;
    private int ImagenCategoriaProducto;

    public ElementoCategoriaProducto(String idCategoriaProducto, String nombreCategoriaProducto, int imagenCategoriaProducto) {
        IdCategoriaProducto = idCategoriaProducto;
        NombreCategoriaProducto = nombreCategoriaProducto;
        ImagenCategoriaProducto = imagenCategoriaProducto;
    }

    public String getIdCategoriaProducto() {
        return IdCategoriaProducto;
    }

    public void setIdCategoriaProducto(String idCategoriaProducto) {
        IdCategoriaProducto = idCategoriaProducto;
    }

    public String getNombreCategoriaProducto() {
        return NombreCategoriaProducto;
    }

    public void setNombreCategoriaProducto(String nombreCategoriaProducto) {
        NombreCategoriaProducto = nombreCategoriaProducto;
    }

    public int getImagenCategoriaProducto() {
        return ImagenCategoriaProducto;
    }

    public void setImagenCategoriaProducto(int imagenCategoriaProducto) {
        ImagenCategoriaProducto = imagenCategoriaProducto;
    }
}
