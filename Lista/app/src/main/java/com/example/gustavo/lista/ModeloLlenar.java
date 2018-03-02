package com.example.gustavo.lista;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Gustavo on 20/02/2018.
 */
@Entity (tableName = "users")
public class ModeloLlenar implements Parcelable{
    @NonNull
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;


    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "lugar")
    private String lugar;

    public ModeloLlenar() {
    }

    @Ignore
    public ModeloLlenar(String nombre,  String descripcion, String lugar) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
    }

    protected ModeloLlenar(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        lugar = in.readString();
    }

    public static final Creator<ModeloLlenar> CREATOR = new Creator<ModeloLlenar>() {
        @Override
        public ModeloLlenar createFromParcel(Parcel in) {
            return new ModeloLlenar(in);
        }

        @Override
        public ModeloLlenar[] newArray(int size) {
            return new ModeloLlenar[size];
        }
    };

    public String getLugar() { return lugar; }

    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(descripcion);
        parcel.writeString(lugar);
    }

    @Override
    public String toString() {
        return new StringBuilder(nombre).append("\n").append(lugar).append("\n").append(descripcion).toString();
    }
}
