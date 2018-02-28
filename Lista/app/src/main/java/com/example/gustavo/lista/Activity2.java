package com.example.gustavo.lista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
//Gustavo De Leon
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        ModeloLlenar modeloLlenar = intent.getParcelableExtra("ejemplo");
        String nombre = modeloLlenar.getNombre();
        String descripcion = modeloLlenar.getDescripcion();
        String lugar = modeloLlenar.getLugar();
        int codigo = modeloLlenar.getCodigo();

        TextView textView1 = findViewById(R.id.texto1_Activity2);
        textView1.setText(nombre);
        TextView textView2 = findViewById(R.id.texto2_Activity2);
        textView2.setText(descripcion);
        TextView textView3 = findViewById(R.id.texto3_Activity2);
        textView3.setText(lugar);
    }
}
