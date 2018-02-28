package com.example.gustavo.lista;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//Gustavo De Leon 17085
//Push
//Cambios
public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private Adapter adapter;
    private ArrayList<ModeloLlenar> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearLista();
        buildListView();

    }

    public void crearLista(){
        model = new ArrayList<>();
        model.add(new ModeloLlenar("Animal-1:",1,"Perro","Casa"));
        model.add(new ModeloLlenar("Animal-2:",2,"Gato","Casa"));
        model.add(new ModeloLlenar("Animal-3:",3,"Oveja","Granja"));
        model.add(new ModeloLlenar("Animal-4:",4,"Caballo","Granja"));
        model.add(new ModeloLlenar("Animal-5:",5,"Vaca","Granja"));

    }

    public void buildListView(){
        adapter = new Adapter(this,model);
        lista = (ListView)findViewById(R.id.listaU);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("ejemplo",model.get(i));
                startActivity(intent);
                try{
                    ModeloLlenar modeloLlenar = (ModeloLlenar) adapter.getItem(i);
                    Log.e("modeloLlenar",modeloLlenar.getCodigo()+"-"+modeloLlenar.getNombre()+"-"+modeloLlenar.getDescripcion()+"-"+modeloLlenar.getLugar());
                    Toast.makeText(getBaseContext(), "El codigo es:"+modeloLlenar.getCodigo(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


}
