package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Repositorio ficticio de elementos
 */
public class ElementosBeneficiariosRepositorio {
    private static ElementosBeneficiariosRepositorio repositorio = new ElementosBeneficiariosRepositorio();
    private HashMap<String, ElementoBeneficiarioDonacion> elementos = new HashMap<>();

    public static ElementosBeneficiariosRepositorio getInstance() {
        return repositorio;
    }

    private ElementosBeneficiariosRepositorio() {
        saveLead(new ElementoBeneficiarioDonacion("1","Carlos Lopez", 35.20, 28.75, R.drawable.lead_photo_2));
        saveLead(new ElementoBeneficiarioDonacion("2","Sara Bone", 20.50, 20.50, R.drawable.lead_photo_3));
        saveLead(new ElementoBeneficiarioDonacion("3","Liliana Paredes", 25.60,20.00, R.drawable.lead_photo_4));
        saveLead(new ElementoBeneficiarioDonacion("4","Benito Peralta", 30.25,29.50, R.drawable.lead_photo_5));
        saveLead(new ElementoBeneficiarioDonacion("5","Juan Jaramillo", 28.50, 21.00, R.drawable.lead_photo_6));
        saveLead(new ElementoBeneficiarioDonacion("6","Alexa Giraldo", 22.50, 15.00, R.drawable.lead_photo_8));
        saveLead(new ElementoBeneficiarioDonacion("7","Linda Murillo", 15.00, 7.00, R.drawable.lead_photo_9));
        saveLead(new ElementoBeneficiarioDonacion("8","Lizeth Astrada", 12.00, 12.00, R.drawable.lead_photo_10));
    }

    private void saveLead(ElementoBeneficiarioDonacion elementoBeneficiarioDonacion) {
        elementos.put(elementoBeneficiarioDonacion.getId(), elementoBeneficiarioDonacion);
    }

    public List<ElementoBeneficiarioDonacion> getElementos() {
        return new ArrayList<>(elementos.values());
    }
}
