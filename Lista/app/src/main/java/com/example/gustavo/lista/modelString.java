package com.example.gustavo.lista;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 3/03/2018.
 */

public class modelString implements Parcelable {
    private final String string1;
    private final String string2;
    private final String string3;
    private final ArrayList<String> lista;

    public modelString (String nombre, String descripcion, String lugar, ArrayList<String> values){
        this.string1 = nombre;
        this.string2 = descripcion;
        this.string3 = lugar;
        this.lista = values;
    }

    public modelString (Parcel parcel){
        string1 = parcel.readString();
        string2 = parcel.readString();
        string3 = parcel.readString();

        lista = new ArrayList<>();
        parcel.readStringList(lista);
    }

    public static final Parcelable.Creator<modelString> CREATOR = new Creator<modelString>(){
        @Override
        public modelString createFromParcel(Parcel parcel){
            return new modelString(parcel);
        }

        @Override
        public modelString[] newArray(int i){
            return new modelString[i];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(string1);
        parcel.writeString(string2);
        parcel.writeString(string3);
        parcel.writeStringList(lista);
    }

    public String getNombre() {
        return string1;
    }

    public String getDescripcion() {
        return string2;
    }

    public String getLugar() {
        return string3;
    }

}
