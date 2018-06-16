package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Repositorio ficticio de elementos
 */
public class ElementosCategoriaProductoRepositorio {
    private static ElementosCategoriaProductoRepositorio repositorio = new ElementosCategoriaProductoRepositorio();
    private HashMap<String, ElementoCategoriaProducto> elementos = new HashMap<>();

    public static ElementosCategoriaProductoRepositorio getInstance() {
        return repositorio;
    }

    private ElementosCategoriaProductoRepositorio() {
        saveLead(new ElementoCategoriaProducto("1","Lácteos", R.drawable.lead_photo_2));
        saveLead(new ElementoCategoriaProducto("2","Carnes", R.drawable.lead_photo_3));
        saveLead(new ElementoCategoriaProducto("3","Conservas", R.drawable.lead_photo_4));
        saveLead(new ElementoCategoriaProducto("4","Pescado", R.drawable.lead_photo_5));
        saveLead(new ElementoCategoriaProducto("5","Hortalizas", R.drawable.lead_photo_6));
        saveLead(new ElementoCategoriaProducto("6","Granos", R.drawable.lead_photo_8));
        saveLead(new ElementoCategoriaProducto("7","Enlatados", R.drawable.lead_photo_9));

    }

    private void saveLead(ElementoCategoriaProducto elementoCategoriaProducto) {
        elementos.put(elementoCategoriaProducto.getIdCategoriaProducto(), elementoCategoriaProducto);
    }

    public List<ElementoCategoriaProducto> getElementos() {
        return new ArrayList<>(elementos.values());
    }
}
