package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IngresoAplicacion extends AppCompatActivity {

    private static final int MY_DATA_CHECK_CODE = 1;
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    private EditText editTextUsuario;
    private EditText editTextContraseña;
    private TextView textViewRegistro;
    private Button btnIngreso;
    private static String id_usuario;
    private static String id_rol;
    private int contadorCompra=1;
    private String contadorCompraString;
    private static IngresoAplicacion INSTANCE;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_aplicacion);
        getSupportActionBar().hide();

        INSTANCE = this;
        editTextUsuario = (EditText) findViewById(R.id.EditText_Usuario);
        editTextUsuario.setBackgroundResource(R.drawable.border_color);

        editTextContraseña = (EditText) findViewById(R.id.EditText_Contrasenia);
        editTextContraseña.setBackgroundResource(R.drawable.border_color);

        textViewRegistro = (TextView) findViewById(R.id.TextView_Registro);

        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),"Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }



        textViewRegistro.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), SeleccionRol.class);
                startActivity(intent);
            }
        });
        //mTts.speak("Bienvenido pantalla de ingreso a la aplicación", TextToSpeech.QUEUE_FLUSH, null);



    }

    public void mostrarInterfazCorrespondienteRol(View view) {
        contadorCompra++;
        final String contadorCompraString=String.valueOf(contadorCompra);
        //Toast.makeText(getApplicationContext(),String.valueOf(contadorCompra),Toast.LENGTH_LONG).show();

        final String id_rol_desde_seleccion_rol=SeleccionRol.getActivityInstance().getIdRol();

            if(TextUtils.isEmpty(editTextUsuario.getText().toString())){
                editTextUsuario.setError("Este campo no puede estar vacío. Por favor ingrese su usuario");
                return;
            }
                if(TextUtils.isEmpty(editTextContraseña.getText().toString())){
                    editTextContraseña.setError("Este campo no puede estar vacío. Por favor ingrese su contraseña");
                    return;
                }else{
                    RequestQueue queue = Volley.newRequestQueue(this);
                    String url3 ="http://192.168.0.9:8080/ProyectoIntegrador/loginApp.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url3,new
                            Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                   if(response.trim().length()==2)
                                    {
                                        Toast.makeText(getApplicationContext(),"Usuario/Contraseña no válidos",Toast.LENGTH_LONG).show();
                                    }
                                    try{
                                        JSONObject obj = new JSONObject(response);
                                        JSONArray ids = obj.getJSONArray("ids");
                                        for(int i=0;i<ids.length();i++){
                                            JSONObject c= ids.getJSONObject(i);
                                            id_usuario = c.getString("ID_USUARIO");
                                            id_rol = c.getString("ID_ROL");

                                            if(id_rol.matches("1")){
                                                Intent intent = new Intent(getApplicationContext(), ListaCategoriaProducto.class);
                                                startActivity(intent);

                                                Intent intent1 = new Intent("PASO_ID_USUARIO").putExtra("ID_USUARIO", id_usuario);


                                                //Toast.makeText(getApplicationContext(),id_rol+" Usuario Beneficiario",Toast.LENGTH_LONG).show();
                                            }else {
                                                if(id_rol.matches("2")){
                                                    Intent intent = new Intent(getApplicationContext(), ListaBeneficiarioDonacion.class);
                                                    startActivity(intent);
                                                    //Toast.makeText(getApplicationContext(),id_rol+" Usuario Donador",Toast.LENGTH_LONG).show();
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
                            params.put("id_rol", id_rol_desde_seleccion_rol);
                            params.put("usuario", editTextUsuario.getText().toString());
                            params.put("password",editTextContraseña.getText().toString());
                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }





            }

    public static IngresoAplicacion getActivityInstance() {
        return INSTANCE;
    }

    public String getIdUsuario()
    {
        return this.id_usuario;
    }

    public String getContadorCompra()
    {
        contadorCompraString=String.valueOf(contadorCompra);
        return this.contadorCompraString;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.
                                    EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);

                    editTextUsuario.setText(strSpeech2Text);
                }
                break;
            default:

                break;
        }
    }
}




