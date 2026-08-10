package model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Conversation implements Serializable {

    private String identificador;
    private long fechaIni;
    private long fechaFin;
    private ArrayList<Message> Messages;
    

    public Conversation() {

    }

    public Conversation(ArrayList<Message> Messages, String identificador) {
        this.identificador = identificador;
        this.fechaIni = Instant.now().getEpochSecond();
        this.fechaFin = 0;
        this.Messages = Messages;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public long getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(long fechaIni) {
        this.fechaIni = fechaIni;
    }

    public long getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(long fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<Message> getMessages() {
        return Messages;
    }

    public void setMessages(ArrayList<Message> Messages) {
        this.Messages = Messages;
    }

    @JsonIgnore
    public int getNumeroMessages() {
        return Messages.size();
    }

    @JsonIgnore
    public String getStartOfMessage() {
        if (Messages.isEmpty()) {
            return null;
        }

        String Message = Messages.get(0).getContenido();
        if (Message.length() <= 20) {
            return Message;
        } else {
            return Message.substring(0, 20);
        }
    }

    @JsonIgnore
    public String getFechaFormat(long fecha) {
        return Model.getFechaFormat(fecha);
    }

}
