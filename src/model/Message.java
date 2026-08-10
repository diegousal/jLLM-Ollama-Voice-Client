package model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message implements Serializable {

    private String emisor;
    private String contenido;
    private long fecha;

    public Message() {

    }

    public Message(String emisor, String contenido) {
        this.emisor = emisor;
        this.contenido = contenido;
        this.fecha = Instant.now().getEpochSecond();
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    @JsonIgnore
    public String getFechaFormat(long fecha) {

        return Model.getFechaFormat(fecha);

    }

    @JsonIgnore
    public String getAsString() {
        return String.format(Locale.ENGLISH,"\n" +emisor + " [" + getFechaFormat(fecha) + "]: " + contenido );
    }

}
