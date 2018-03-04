package com.example.gustavo.lista;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Gustavo on 3/03/2018.
 */

public class Informacion {

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "lugar")
    private String lugar;

    public Informacion(String descripcion, String lugar) {
        this.descripcion = descripcion;
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}