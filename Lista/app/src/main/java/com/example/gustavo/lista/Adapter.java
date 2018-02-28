package com.example.gustavo.lista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gustavo on 20/02/2018.
 */

public class Adapter extends BaseAdapter {
    private  Context contexto;
    private ArrayList<ModeloLlenar> arrayList;

    public Adapter(Context context, ArrayList<ModeloLlenar> arrayList){
        this.contexto =context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null){
            LayoutInflater layoutInflater =(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item,null);
        }

        TextView nombre = (TextView)convertView.findViewById(R.id.nombre);
        TextView descripcion = (TextView)convertView.findViewById(R.id.descripcion);
        TextView lugar = (TextView)convertView.findViewById(R.id.lugar);

        nombre.setText(arrayList.get(i).getNombre());
        descripcion.setText(arrayList.get(i).getDescripcion());
        lugar.setText(arrayList.get(i).getLugar());

        return convertView;
    }
}
