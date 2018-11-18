package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaCategoriaProducto extends AppCompatActivity {

    private GridView gridViewCategoriaProducto;
    private ArrayList<String> GridViewItems = new ArrayList<String>();
    private ArrayList<String> GridViewImagenes = new ArrayList<String>();
    DatabaseHandlerCategorias db = new DatabaseHandlerCategorias(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categoria_producto);

        DatabaseHandlerProducto db1 = new DatabaseHandlerProducto(getApplicationContext());
        db1.deleteProductos();

        GridViewItems=db.getAllCategorias(1);
        GridViewImagenes=db.getAllCategorias(2);

        String []arregloCategorias=GridViewItems.toArray(new String[0]);
        String []arregloImagenes=GridViewImagenes.toArray(new String[0]);

        Toast.makeText(getApplicationContext(),db.getAllCategorias(1).toString(),Toast.LENGTH_SHORT).show();
        ElementosCategoriaProductoAdaptador adapter = new ElementosCategoriaProductoAdaptador(this, arregloCategorias, "CategoriaProductos", arregloImagenes);
        final GridView gridViewCategoriaProducto = (GridView) findViewById(R.id.GridView_Categoria_Productos);

        gridViewCategoriaProducto.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        gridViewCategoriaProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) gridViewCategoriaProducto.getItemAtPosition(position);
                final ElementoCategoriaProducto categoriaProducto = db.getCategoriaNombre(item);
                //Toast.makeText(getApplicationContext(),categoriaProducto.getIdCategoriaProducto(),Toast.LENGTH_SHORT).show();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url2 ="http://192.168.0.9:8080/ProyectoIntegrador/producto.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                                        try {
                                            JSONObject jsonObj = new
                                                    JSONObject(response.toString());

                                            JSONArray contacts = jsonObj.getJSONArray("producto");
                                            for (int i = 0; i < contacts.length(); i++) {
                                                JSONObject c = contacts.getJSONObject(i);

                                                String id = c.getString("ID_PRODUCTO");
                                                String id_categoria_producto  = c.getString("ID_CATEGORIA_PRODUCTO");
                                                String nombre_producto = c.getString("NOMBRE_PRODUCTO");
                                                String detalle_producto = c.getString("DETALLE_PRODUCTO");
                                                String precio_unitario=c.getString("PRECIO_UNITARIO");
                                                String imagen_producto = c.getString("IMAGEN_PRODUCTO");
                                                DatabaseHandlerProducto db = new DatabaseHandlerProducto(getApplicationContext());
                                                db.addProductos(new ElementoProducto(id, id_categoria_producto,nombre_producto,detalle_producto,precio_unitario,imagen_producto));
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
                        }){
                    @Override
                    protected Map<String,String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("idCategoria", categoriaProducto.getIdCategoriaProducto());
                        return params;
                    }
                };
                queue.add(stringRequest);

                Intent intent = new Intent(getApplicationContext(), ListaProductos.class);
                startActivity(intent);
            }
        });

    }










}
