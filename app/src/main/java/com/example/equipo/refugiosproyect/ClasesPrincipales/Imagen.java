package com.example.equipo.refugiosproyect.ClasesPrincipales;

import android.os.Parcel;
import android.os.Parcelable;

public class Imagen implements Parcelable {

    //ATRIBUTOS-------------------------------------------------------------
    private String id_foto;
    private String url;

    //CONSTRUCTORES----------------------------------------------------------
    public Imagen() {
    }

    public Imagen(String id_foto, String url) {
        this.id_foto = id_foto;
        this.url = url;
    }

    //GETTERS Y SETTERS------------------------------------------------------


    public String getId_foto() {
        return id_foto;
    }

    public void setId_foto(String id_foto) {
        this.id_foto = id_foto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    //IMPLEMENTACION-----------------------------------------------------------

    public static Imagen[] getImagenes() {
        return new Imagen[]{
                new Imagen("1","http://i.imgur.com/zuG2bGQ.jpg"),
                new Imagen("2","http://i.imgur.com/ovr0NAF.jpg"),
                new Imagen("3","http://i.imgur.com/n6RfJX2.jpg"),
                new Imagen("4","http://i.imgur.com/qpr5LR2.jpg"),
                new Imagen("5","http://i.imgur.com/pSHXfu5.jpg"),
                new Imagen("6","http://i.imgur.com/3wQcZeY.jpg"),
        };
    }

    protected Imagen(Parcel in) {
        id_foto = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_foto);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Imagen> CREATOR = new Creator<Imagen>() {
        @Override
        public Imagen createFromParcel(Parcel in) {
            return new Imagen(in);
        }

        @Override
        public Imagen[] newArray(int size) {
            return new Imagen[size];
        }
    };
}
