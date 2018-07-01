package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SeleccionRol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_rol);

        DatabaseHandlerCategorias db = new DatabaseHandlerCategorias(getApplicationContext());
        db.deleteCategorias();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url2 ="http://192.168.0.6:8080/ProyectoIntegrador/categoriaProducto.php";
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url2, null, new
                        Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONObject jsonObj = new
                                            JSONObject(response.toString());

                                    JSONArray contacts = jsonObj.getJSONArray("categoriaProducto");
                                    for (int i = 0; i < contacts.length(); i++) {
                                        JSONObject c = contacts.getJSONObject(i);

                                        String id = c.getString("ID_CATEGORIA_PRODUCTO");
                                        String nombre_categoria  = c.getString("NOMBRE_CATEGORIA_PRODUCTO");
                                        String imagen_categoria = c.getString("IMAGEN_CATEGORIA");
                                        DatabaseHandlerCategorias db1 = new DatabaseHandlerCategorias(getApplicationContext());
                                        db1.addCategoria(new ElementoCategoriaProducto(id, nombre_categoria, imagen_categoria));
                                        //Log.d("Insert","Contacto insertado correctamente");
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
        queue.add(jsObjRequest);



    }

    public void invocarActividadUsuario(View view)
    {
        Intent intent = new Intent(this,IngresoAplicacion.class);
        startActivity(intent);
    }

    public void invocarActividadDonador(View view)
    {
        Intent intent = new Intent(this,IngresoAplicacion.class);
        startActivity(intent);
    }
}
