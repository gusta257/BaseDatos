package com.example.gustavo.lista;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Gustavo on 20/02/2018.
 */
@Entity (tableName = "users")
public class ModeloLlenar implements Parcelable {
    @NonNull
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "codigo")
    private int codigo;

    @ColumnInfo(name = "descripcion")
    private String descripcion;
    
    @ColumnInfo(name = "lugar")
    private String lugar;

    public ModeloLlenar(String nombre, int codigo, String descripcion, String lugar) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.lugar = lugar;
    }

    protected ModeloLlenar(Parcel in) {
        nombre = in.readString();
        codigo = in.readInt();
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
        parcel.writeInt(codigo);
        parcel.writeString(descripcion);
        parcel.writeString(lugar);
    }
}
