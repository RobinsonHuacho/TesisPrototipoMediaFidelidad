package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfirmacionDonacion extends AppCompatActivity {

    DatabaseHandlerCompras db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_donacion);

        db1 = new DatabaseHandlerCompras(getApplicationContext());
        db1.deleteProductos();

        RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
        String url3 ="http://192.168.0.11:8080/ProyectoIntegrador/compras.php";
        StringRequest jsObjRequest1 = new StringRequest
                (Request.Method.GET, url3, new
                        Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObj1 = new
                                            JSONObject(response.toString());
                                    //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                    JSONArray contacts = jsonObj1.getJSONArray("compras");
                                    for (int i = 0; i < contacts.length(); i++) {
                                        JSONObject c = contacts.getJSONObject(i);

                                        String id_compra = c.getString("ID_COMPRA");
                                        String id_usuario  = c.getString("ID_USUARIO");
                                        String fecha_compra = c.getString("FECHA_COMPRA");
                                        String total_compra = c.getString("TOTAL_COMPRA");
                                        String saldo_compra = c.getString("SALDO_COMPRA");
                                        String estado_compra = c.getString("ESTADO_COMPRA");
                                        String id_rol = c.getString("ID_ROL");
                                        String foto_usuario = c.getString("FOTO_USUARIO");
                                        String primer_nombre_usuario = c.getString("PRIMER_NOMBRE_USUARIO");
                                        String segundo_nombre_usuario = c.getString("SEGUNDO_NOMBRE_USUARIO");
                                        String primer_apellido_usuario = c.getString("PRIMER_APELLIDO_USUARIO");
                                        String segundo_apellido_usuario = c.getString("SEGUNDO_APELLIDO_USUARIO");
                                        String direccion_usuario = c.getString("DIRECCION_USUARIO");
                                        String telefono_usuario = c.getString("TELEFONO_USUARIO");
                                        String email_usuario = c.getString("EMAIL_USUARIO");
                                        String usuario_aplicativo = c.getString("USUARIO_APLICATIVO");
                                        String password_aplicativo = c.getString("PASSWORD_APLICATIVO");
                                        String nombres = c.getString("NOMBRES");

                                            //Toast.makeText(getApplicationContext(),nombres,Toast.LENGTH_LONG).show();
                                            db1 = new DatabaseHandlerCompras(getApplicationContext());
                                            db1.addCompras(new ElementoCompra(id_compra, id_usuario, fecha_compra, total_compra, saldo_compra, estado_compra, id_rol, foto_usuario, primer_nombre_usuario, segundo_nombre_usuario, primer_apellido_usuario, segundo_apellido_usuario, direccion_usuario, telefono_usuario, email_usuario, usuario_aplicativo, password_aplicativo, nombres));
                                            //Log.d("Insert","Contacto insertado correctamente");
                                            db1.deleteProductos();
                                        }

                                }
                                catch (final JSONException e) {
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!");
                    }
                });
        queue1.add(jsObjRequest1);

    }

    public void regresarListaBeneficiarios(View view)
    {
        Intent intent = new Intent(this,ListaBeneficiarioDonacion.class);
        startActivity(intent);
    }
}
