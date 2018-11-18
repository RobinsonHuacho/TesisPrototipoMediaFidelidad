package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mercadopago.customviews.MPEditText;

import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.EXTRA_INDEX;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Donacion extends AppCompatActivity {

    private MPEditText EditTextMonto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donacion);

        EditTextMonto=(MPEditText) findViewById(R.id.EditText_Monto);

        // Init MercadoPago object with public key


    }

    public void makeRequestInsertarDonacion(View view){
        final Intent intent = getIntent();
        final String messageIdCompra = intent.getStringExtra(EXTRA_INDEX);
        final String messageIdUsuario = intent.getStringExtra(EXTRA_MESSAGE);
        //Toast.makeText(getApplicationContext(),messageIdCompra,Toast.LENGTH_LONG).show();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.11:8080/ProyectoIntegrador/nuevaDonacion.php";
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
                params.put("id_compra", messageIdCompra);
                params.put("id_usuario", messageIdUsuario);
                params.put("monto", EditTextMonto.getText().toString());



                return params;
            }
        };
        queue.add(stringRequest);
        Toast.makeText(getApplicationContext(),"Donación Realizada Exitosamente",Toast.LENGTH_LONG).show();

        Intent intent1 = new Intent(this, ConfirmacionDonacion.class);
        startActivity(intent1);


    }

    public void volverCatalogo(View view){
        Intent intent = new Intent(this, ListaProductos.class);
        startActivity(intent);
    }
}
