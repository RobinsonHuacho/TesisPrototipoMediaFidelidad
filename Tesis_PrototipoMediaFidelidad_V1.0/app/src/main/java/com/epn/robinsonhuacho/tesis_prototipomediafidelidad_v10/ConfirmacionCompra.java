package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.EXTRA_INDEX;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ConfirmacionCompra extends AppCompatActivity {

    private TextView textViewPrecio;
    private TextView textViewNombreProducto;
    private TextView textViewDescripcionProducto;
    private EditText editTextCantidad;
    private TextView textViewSubtotal;
    private Button incrementarCantidad;
    private Button disminuirCantidad;
    private int contador=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_compra);

        Intent intent = getIntent();
        String messageNombre = intent.getStringExtra(EXTRA_MESSAGE);
        String messageDescripcion=intent.getStringExtra(Intent.EXTRA_TITLE);
        String messagePrecio=intent.getStringExtra(Intent.EXTRA_TEXT);

        //Toast.makeText(getApplicationContext(),messageNombre+" "+messageDescripcion+" "+messagePrecio, Toast.LENGTH_LONG).show();

        textViewNombreProducto = (TextView) findViewById(R.id.TextView_NombreProducto);
        textViewNombreProducto.setText(messageNombre);

        textViewDescripcionProducto = (TextView) findViewById(R.id.TextView_Descripcion);
        textViewDescripcionProducto.setText(messageDescripcion);

        textViewPrecio = (TextView) findViewById(R.id.TextView_PrecioProducto);
        textViewPrecio.setText(messagePrecio);


        textViewSubtotal=(TextView) findViewById(R.id.TextView_Subtotal);
        textViewSubtotal.setText(messagePrecio);

        editTextCantidad = (EditText) findViewById(R.id.EditText_Cantidad);
        editTextCantidad.setText(String.valueOf(contador));



        incrementarCantidad = (Button) findViewById(R.id.buttonIncrementar);
        disminuirCantidad = (Button) findViewById(R.id.buttonDisminuir);

        incrementarCantidad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                contador++;
                editTextCantidad.setText(String.valueOf(contador));
                textViewSubtotal.setText(String.valueOf(Float.parseFloat(editTextCantidad.getText().toString())*Float.parseFloat(textViewPrecio.getText().toString())));
            }
        });

        disminuirCantidad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                contador--;
                editTextCantidad.setText(String.valueOf(contador));
                textViewSubtotal.setText(String.valueOf(Float.parseFloat(editTextCantidad.getText().toString())*Float.parseFloat(textViewPrecio.getText().toString())));
            }
        });
    }

    public void makeRequestInsertarProducto(View view){


        final Intent intent = getIntent();
        final String messageId = intent.getStringExtra(EXTRA_INDEX);
        //Toast.makeText(getApplicationContext(),"ID_PRODUCTO: "+messageId,Toast.LENGTH_LONG).show();

        final String messageIdUsuario=IngresoAplicacion.getActivityInstance().getIdUsuario();
        //Toast.makeText(getApplicationContext(), "ID_USUARIO: "+messageIdUsuario, Toast.LENGTH_LONG).show();

        final String contadorCompra=IngresoAplicacion.getActivityInstance().getContadorCompra();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.9:8080/ProyectoIntegrador/nuevoDetalle.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), messageId+" "+textViewPrecio.getText().toString()+" "+editTextCantidad.getText().toString()+" "+textViewSubtotal.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<String, String>();
                params.put("id_compra", contadorCompra);
                params.put("id_producto", messageId);
                params.put("id_usuario", messageIdUsuario);
                params.put("cantidad", editTextCantidad.getText().toString());
                params.put("precio", textViewPrecio.getText().toString());
                params.put("subtotal", textViewSubtotal.getText().toString());


                return params;
            }
        };
        queue.add(stringRequest);
        Toast.makeText(getApplicationContext(),"Producto Ingresado Exitosamente",Toast.LENGTH_LONG).show();

        Intent intent1 = new Intent(this, ListaProductos.class);
        startActivity(intent1);
    }

    public void volverCatalogo(View view){
        Intent intent = new Intent(this, ListaProductos.class);
        startActivity(intent);
    }
}

