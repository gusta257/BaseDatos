package com.example.gustavo.lista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Gustavo on 3/03/2018.
 */

public class Activity2 extends AppCompatActivity {

    public static final String SELECTED_ITEM = "com.jwhh.jim.notekeeper.NOTE_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        modelString itemToRecieve = intent.getParcelableExtra(SELECTED_ITEM);


        String nombre = itemToRecieve.getNombre();
        String descripcion = itemToRecieve.getDescripcion();
        String lugar = itemToRecieve.getLugar();


        TextView textView1 = findViewById(R.id.texto1_Activity2);
        textView1.setText(nombre);
        TextView textView2 = findViewById(R.id.texto2_Activity2);
        textView2.setText(lugar );
        TextView textView3 = findViewById(R.id.texto3_Activity2);
        textView3.setText(descripcion);
    }
}
