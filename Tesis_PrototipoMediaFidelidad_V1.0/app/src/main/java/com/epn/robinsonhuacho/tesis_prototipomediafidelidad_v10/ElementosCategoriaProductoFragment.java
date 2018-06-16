package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


/**
 * Vista para los leads del CRM
 */
public class ElementosCategoriaProductoFragment extends Fragment {
    private GridView listaElementos;
    private ElementosCategoriaProductoAdaptador elementosCategoriaProductoAdaptador;

    public ElementosCategoriaProductoFragment() {
        // Required empty public constructor
    }

    public static ElementosCategoriaProductoFragment newInstance(/*parámetros*/) {
        ElementosCategoriaProductoFragment fragmento = new ElementosCategoriaProductoFragment();
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
        View root = inflater.inflate(R.layout.activity_lista_categoria_producto, container, false);

        // Instancia del ListView.
        listaElementos = (GridView) root.findViewById(R.id.GridView_Categoria_Productos);

        // Inicializar el adaptador con la fuente de datos.
        elementosCategoriaProductoAdaptador = new ElementosCategoriaProductoAdaptador(getActivity(),
                ElementosCategoriaProductoRepositorio.getInstance().getElementos());

        //Relacionando la lista con el adaptador
        listaElementos.setAdapter(elementosCategoriaProductoAdaptador);

        // Eventos
        listaElementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ElementoCategoriaProducto currentElementoCategoriaProducto = elementosCategoriaProductoAdaptador.getItem(position);
                Toast.makeText(getActivity(),
                        "Iniciar screen de detalle para: \n" + currentElementoCategoriaProducto.getNombreCategoriaProducto(),
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
            elementosCategoriaProductoAdaptador.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
