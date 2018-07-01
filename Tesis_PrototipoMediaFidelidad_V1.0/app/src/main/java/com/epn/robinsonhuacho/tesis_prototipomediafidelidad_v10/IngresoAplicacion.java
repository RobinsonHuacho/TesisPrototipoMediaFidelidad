package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class IngresoAplicacion extends AppCompatActivity {

    private EditText editTextUsuario;
    private EditText editTextContraseña;
    private Button btnIngreso;
    private static String id_usuario;
    private static String id_rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_aplicacion);
        getSupportActionBar().hide();

        editTextUsuario = (EditText) findViewById(R.id.EditText_Usuario);
        editTextContraseña = (EditText) findViewById(R.id.EditText_Contrasenia);
    }

    public void mostrarInterfazCorrespondienteRol(View view) {

            if(TextUtils.isEmpty(editTextUsuario.getText().toString())){
                editTextUsuario.setError("Este campo no puede estar vacío. Por favor ingrese su usuario");
                return;
            }
                if(TextUtils.isEmpty(editTextContraseña.getText().toString())){
                    editTextContraseña.setError("Este campo no puede estar vacío. Por favor ingrese su contraseña");
                    return;
                }else{
                    RequestQueue queue = Volley.newRequestQueue(this);
                    String url3 ="http://192.168.0.6:8080/ProyectoIntegrador/loginApp.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url3,new
                            Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try{
                                        JSONObject obj = new JSONObject(response);
                                        JSONArray ids = obj.getJSONArray("ids");
                                        for(int i=0;i<ids.length();i++){
                                            JSONObject c= ids.getJSONObject(i);
                                            id_usuario = c.getString("ID_USUARIO");
                                            id_rol = c.getString("ID_ROL");
                                            //Toast.makeText(getApplicationContext(),id_rol,Toast.LENGTH_LONG).show();

                                            if(id_rol.matches("1")){
                                                Intent intent = new Intent(getApplicationContext(), ListaCategoriaProducto.class);
                                                intent.putExtra(EXTRA_MESSAGE, id_usuario);
                                                startActivity(intent);
                                                Toast.makeText(getApplicationContext(),id_rol+" Usuario Beneficiario",Toast.LENGTH_LONG).show();
                                            }else {
                                                if(id_rol.matches("2")){
                                                    Intent intent = new Intent(getApplicationContext(), ListaBeneficiarioDonacion.class);
                                                    intent.putExtra(EXTRA_MESSAGE, id_usuario);
                                                    startActivity(intent);
                                                    Toast.makeText(getApplicationContext(),id_rol+" Usuario Donador",Toast.LENGTH_LONG).show();
                                                }

                                            }
                                        }
                                    }catch (Throwable t){

                                    }
}
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //nombreTextView.setText("That didn't work!");
                        }
                    }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();

                            params.put("usuario", editTextUsuario.getText().toString());
                            params.put("password",editTextContraseña.getText().toString());
                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }





            }
}




