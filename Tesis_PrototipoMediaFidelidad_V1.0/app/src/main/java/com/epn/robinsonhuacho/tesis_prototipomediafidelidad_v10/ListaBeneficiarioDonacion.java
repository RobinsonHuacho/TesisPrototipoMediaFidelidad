package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;


import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ListaBeneficiarioDonacion extends AppCompatActivity {


    private ArrayList<String> ListViewItemsIDCompra = new ArrayList<String>();
    private ArrayList<String> ListViewItemsIDUsuario = new ArrayList<String>();
    private ArrayList<String> ListViewItemsNombreUsuario = new ArrayList<String>();
    private ArrayList<String> ListViewItemsTotalCompra = new ArrayList<String>();
    private ArrayList<String> ListViewItemsSaldoCompra = new ArrayList<String>();
    private ArrayList<String> ListViewItemsEstadoCompra = new ArrayList<String>();
    private ArrayList<String> ListViewImagenes = new ArrayList<String>();

    private String selectedIdCompra, selectedIdUsuario;

    DatabaseHandlerCompras db = new DatabaseHandlerCompras(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_beneficiario_donacion);

        ListViewItemsIDCompra=db.getAllProductos(0);
        ListViewItemsIDUsuario=db.getAllProductos(1);
        ListViewItemsNombreUsuario=db.getAllProductos(17);
        ListViewItemsTotalCompra=db.getAllProductos(3);
        ListViewItemsSaldoCompra=db.getAllProductos(4);
        ListViewImagenes=db.getAllProductos(7);

        //Toast.makeText(getApplicationContext(),db.getAllProductos(7).toString(),Toast.LENGTH_LONG).show();

        final String[] arregloIDCompra =ListViewItemsIDCompra.toArray(new String[0]);
        final String[] arregloIDUsuario =ListViewItemsIDUsuario.toArray(new String[0]);
        final String[] arregloNombres = ListViewItemsNombreUsuario.toArray(new String[0]);
        String[] arregloProductos =ListViewItemsTotalCompra.toArray(new String[0]);
        String[] arregloDescripcion = ListViewItemsSaldoCompra.toArray(new String[0]);
        String[] arregloImagenes = ListViewImagenes.toArray(new String[0]);

        //Toast.makeText(getApplicationContext(),String.valueOf(db.conteoRegistros()), Toast.LENGTH_LONG).show();

        if(db.conteoRegistros()!=0) {
            final ElementosComprasAdaptador adapter = new ElementosComprasAdaptador(this, arregloNombres, arregloProductos, arregloDescripcion, "Beneficiarios", arregloImagenes);
            final ListView gridView = (ListView) findViewById(R.id.ListView_Beneficiarios);

            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedIdCompra=arregloIDCompra[position].toString();
                    selectedIdUsuario=arregloIDUsuario[position].toString();

                    Intent intent = new Intent(getApplicationContext(), Donacion.class);

                    intent.putExtra(Intent.EXTRA_INDEX, selectedIdCompra);
                    intent.putExtra(EXTRA_MESSAGE, selectedIdUsuario);

                    Toast.makeText(getApplicationContext(),selectedIdCompra+" "+selectedIdUsuario, Toast.LENGTH_LONG).show();

                    startActivity(intent);
                }
            });


        }else
            {
                TextView textView3 = (TextView) findViewById(R.id.textView3);
                textView3.setVisibility(View.GONE);
                ListView ListView_Beneficiarios = (ListView) findViewById(R.id.ListView_Beneficiarios);
                ListView_Beneficiarios.setVisibility(View.GONE);

                ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
                imageView3.setVisibility(View.VISIBLE);
                TextView textView4 = (TextView) findViewById(R.id.textView4);
                textView4.setVisibility(View.VISIBLE);
            }





    }
}
