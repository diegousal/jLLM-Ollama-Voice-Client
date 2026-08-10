package model;


public class Frase {
    private String tipo;
    private int longitud;
    private String contenido;

    public Frase(String tipo, int longitud, String contenido) {
        this.tipo = tipo;
        this.longitud = longitud;
        this.contenido = contenido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


}
