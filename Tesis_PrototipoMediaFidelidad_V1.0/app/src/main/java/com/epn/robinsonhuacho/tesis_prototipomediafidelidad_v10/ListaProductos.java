package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ListaProductos extends AppCompatActivity {

    GridView gridView;
    //public String idCategoriaProducto;
    private ArrayList<String> gridViewItemsID = new ArrayList<String>();
    private ArrayList<String> gridViewItemsNombre = new ArrayList<String>();
    private ArrayList<String> gridViewItemsDetalle = new ArrayList<String>();
    private ArrayList<String> gridViewItemsPrecio = new ArrayList<String>();
    private ArrayList<String> gridViewItemsImagen = new ArrayList<String>();

    private ArrayAdapter<String> adapter;
    private String selectedId, selectedItem, selectedDescription, selectedPrecio;
    // ArrayList<String> selectedItems;
    DatabaseHandlerProducto db = new DatabaseHandlerProducto(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        //Intent intent = getIntent();
        //idCategoriaProducto=intent.getStringExtra(EXTRA_MESSAGE);
        //TextView tv = (TextView) findViewById(R.id.textView4);
        //tv.setText(idCategoriaProducto);

        gridViewItemsID = db.getAllProductos(0);
        gridViewItemsNombre = db.getAllProductos(2);
        gridViewItemsDetalle = db.getAllProductos(3);
        gridViewItemsPrecio = db.getAllProductos(4);
        gridViewItemsImagen = db.getAllProductos(5);

        Toast.makeText(getApplicationContext(),gridViewItemsImagen.toString(), Toast.LENGTH_LONG).show();

        final String[] arregloID = gridViewItemsID.toArray(new String[0]);
        String[] arregloProductos = gridViewItemsNombre.toArray(new String[0]);
        String[] arregloDescripcion = gridViewItemsDetalle.toArray(new String[0]);
        String[] arregloImagenes = gridViewItemsImagen.toArray(new String[0]);
        String[] arregloPrecios = gridViewItemsPrecio.toArray(new String[0]);

        final ElementosProductosAdaptador adapter = new ElementosProductosAdaptador(this, arregloProductos, arregloDescripcion, arregloPrecios, "Productos", arregloImagenes);
        final GridView gridView = (GridView) findViewById(R.id.GridView_Productos);

        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //db.deleteProductos();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) gridView.getItemAtPosition(position);
                final ElementoProducto categoriaProducto = db.getProductoNombre(item);

                LinearLayout ll = (LinearLayout) view;
                TextView tv = (TextView) ll.findViewById(R.id.TextView_Nombre);
                TextView tv1 = (TextView) ll.findViewById(R.id.TextView_Descripcion);
                TextView tv2 = (TextView) ll.findViewById(R.id.TextView_Precio);

                selectedId = arregloID[position].toString();
                selectedItem = tv.getText().toString();
                selectedDescription = tv1.getText().toString();
                selectedPrecio = tv2.getText().toString();


                Intent intent = new Intent(getApplicationContext(), ConfirmacionCompra.class);
                intent.putExtra(Intent.EXTRA_INDEX, selectedId);
                intent.putExtra(EXTRA_MESSAGE, selectedItem);
                intent.putExtra(Intent.EXTRA_TITLE, selectedDescription);
                intent.putExtra(Intent.EXTRA_TEXT, selectedPrecio);

                //Toast.makeText(getApplicationContext(),selectedId+" "+selectedItem+" "+selectedDescription+" "+selectedPrecio, Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        });
    }


}
