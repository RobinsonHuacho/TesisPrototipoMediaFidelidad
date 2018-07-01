package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Intent.EXTRA_TEXT;
import static android.content.Intent.EXTRA_TITLE;
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
        String messageDescripcion=intent.getStringExtra(EXTRA_TITLE);
        String messagePrecio=intent.getStringExtra(EXTRA_TEXT);

        textViewNombreProducto = (TextView) findViewById(R.id.TextView_NombreProducto);
        textViewNombreProducto.setText(messageNombre);

        textViewDescripcionProducto = (TextView) findViewById(R.id.TextView_Descripcion);
        textViewDescripcionProducto.setText(messageDescripcion);

        textViewPrecio = (TextView) findViewById(R.id.TextView_PrecioProducto);
        textViewPrecio.setText(messagePrecio);


        textViewSubtotal=(TextView) findViewById(R.id.TextView_Subtotal);
        //textViewSubtotal.setText(String.valueOf(Integer.parseInt(editTextCantidad.getText().toString())*Float.parseFloat(textViewPrecio.getText().toString())));

        editTextCantidad = (EditText) findViewById(R.id.EditText_Cantidad);
        editTextCantidad.setText(String.valueOf(contador));

        incrementarCantidad = (Button) findViewById(R.id.buttonIncrementar);
        disminuirCantidad = (Button) findViewById(R.id.buttonDisminuir);

        incrementarCantidad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                contador++;
                editTextCantidad.setText(String.valueOf(contador));
                textViewSubtotal.setText(String.valueOf(Integer.parseInt(editTextCantidad.getText().toString())*Float.parseFloat(textViewPrecio.getText().toString())));
            }
        });

        disminuirCantidad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                contador--;
                editTextCantidad.setText(String.valueOf(contador));
                textViewSubtotal.setText(String.valueOf(Integer.parseInt(editTextCantidad.getText().toString())*Float.parseFloat(textViewPrecio.getText().toString())));
            }
        });
    }
}
