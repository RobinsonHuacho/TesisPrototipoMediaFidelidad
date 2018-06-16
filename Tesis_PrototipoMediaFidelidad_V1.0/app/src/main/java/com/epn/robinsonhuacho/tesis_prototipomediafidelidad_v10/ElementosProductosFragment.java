package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


/**
 * Vista para los leads del CRM
 */
public class ElementosProductosFragment extends Fragment {
    private GridView listaElementos;
    private ElementosProductosAdaptador elementosProductosAdaptador;

    public ElementosProductosFragment() {
        // Required empty public constructor
    }

    public static ElementosProductosFragment newInstance(/*parámetros*/) {
        ElementosProductosFragment fragmento = new ElementosProductosFragment();
        // Setup parámetros
        return fragmento;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Gets parámetros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_lista_productos, container, false);

        // Instancia del ListView.
        listaElementos = (GridView) root.findViewById(R.id.GridView_Productos);

        // Inicializar el adaptador con la fuente de datos.
        elementosProductosAdaptador = new ElementosProductosAdaptador(getActivity(),
                ElementosProductosRepositorio.getInstance().getElementos());

        //Relacionando la lista con el adaptador
        listaElementos.setAdapter(elementosProductosAdaptador);

        // Eventos
        listaElementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ElementoProducto currentElemento = elementosProductosAdaptador.getItem(position);
                Toast.makeText(getActivity(),
                        "Iniciar screen de detalle para: \n" + currentElemento.getNombres(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        setHasOptionsMenu(true);
        return root;
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_leads_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_all) {
            // Eliminar todos los leads
            elementosProductosAdaptador.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
