package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Repositorio ficticio de elementos
 */
public class ElementosProductosRepositorio {
    private static ElementosProductosRepositorio repositorio = new ElementosProductosRepositorio();
    private HashMap<String, ElementoProducto> elementos = new HashMap<>();

    public static ElementosProductosRepositorio getInstance() {
        return repositorio;
    }

    private ElementosProductosRepositorio() {
        saveLead(new ElementoProducto("1","1","Leche", "Entera",1.15,0, R.drawable.lead_photo_2));
        saveLead(new ElementoProducto("2","1","Queso", "Fresco",3.50,0, R.drawable.lead_photo_3));
        saveLead(new ElementoProducto("3","1","Yogurt", "Fresa",0.50,0, R.drawable.lead_photo_4));
        saveLead(new ElementoProducto("4","2","Atún", "Real",1.25,0, R.drawable.lead_photo_5));
        saveLead(new ElementoProducto("5","2","Sardina", "Real",1.65,0, R.drawable.lead_photo_6));
        saveLead(new ElementoProducto("6","3","Fideos", "Sumesa",2.20,0, R.drawable.lead_photo_8));
        saveLead(new ElementoProducto("7","4","Sal", "Real",1.50,0,R.drawable.lead_photo_9));
        saveLead(new ElementoProducto("8","4","Azucar", "Real",1.50,2, R.drawable.lead_photo_10));
    }

    private void saveLead(ElementoProducto elemento) {
        elementos.put(elemento.getIdProducto(), elemento);
    }

    public List<ElementoProducto> getElementos() {
        return new ArrayList<>(elementos.values());
    }
}
