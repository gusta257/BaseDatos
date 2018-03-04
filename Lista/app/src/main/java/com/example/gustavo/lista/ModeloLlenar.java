package com.example.gustavo.lista;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
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
public class ModeloLlenar {
    @NonNull
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @Embedded
    private Informacion info;

    public ModeloLlenar() {
    }

    @Ignore
    public ModeloLlenar(@NonNull int id,String nombre, Informacion info) {
        this.id = id;
        this.nombre = nombre;
        this.info = info;
    }
    @Ignore
    public ModeloLlenar(String nombre, Informacion info) {
        this.nombre = nombre;
        this.info = info;
    }

    protected ModeloLlenar(Parcel in) {
        nombre = in.readString();

    }

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

    public Informacion getInfo() {
        return info;
    }

    public void setInfo(Informacion info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return new StringBuilder(nombre).toString();
    }
}

