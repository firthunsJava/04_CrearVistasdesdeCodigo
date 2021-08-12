package com.firthuns.a04_crearvistasdesdecodigo.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Alumno implements Parcelable {
    private String nombre;
    private String Apellidos;

    public Alumno(String nombre, String apellidos) {
        this.nombre = nombre;
        Apellidos = apellidos;
    }

    protected Alumno(Parcel in) {
        nombre = in.readString();
        Apellidos = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(Apellidos);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }
}
