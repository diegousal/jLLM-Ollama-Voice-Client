package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Model {

    private final IRepository repository;
    private final ILLM llm;
    File ficheroEstado;
    List<Conversation> conversations = new ArrayList<>();

    public Model(IRepository repository, ILLM llm) {

        this.repository = repository;
        this.llm = llm;
        ficheroEstado = Paths.get(System.getProperty("user.home"), "Desktop", "jLLM", "jLLM.bin").toFile();
    }

    public boolean exportStatus() {

        if (ficheroEstado.isFile() && ficheroEstado.exists()) {
            System.err.println("El fichero no existe,creando...");
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroEstado));) {
            oos.writeObject(conversations);
            return true;
        } catch (IOException ex) {
            System.err.println("Error durante la serialización: " + ex.getMessage());
            return false;
        } 

    }

    public int loadStatus() {
     
        if (ficheroEstado.exists() && ficheroEstado.isFile()) {
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEstado));) {
                conversations = (List<Conversation>) ois.readObject();
                return conversations.size();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("Error durante la deserialización: " + ex.getMessage());
                return -1;
            } 
        }
        return -1;
    }

    public boolean elConversation(int i) {
        conversations.remove(i);
        return true;
    }

    public boolean importConversation() {
        List<Conversation> temp = repository.importConversations();
        if (temp == null) {
            System.err.println("El archivo no existe");
            return false;
        } else {
            conversations.addAll(temp);
            return true;
        }

    }

    public boolean exportConversation() {
        repository.exportConversations(conversations);
        return true;
    }

    public List<Conversation> getConversation() {
        return conversations;
    }

    public Conversation newConversation() {
        ArrayList<Message> Messages = new ArrayList<>();
        Conversation conversation = new Conversation(Messages, llm.getIdentifier());
        conversations.add(conversation);
        return conversation;
    }

    public void addMessage(String emisor, String contenido, Conversation conversation) {
        ArrayList<Message> temp = conversation.getMessages();
        temp.add(new Message(emisor, contenido));
        conversation.setMessages(temp);
    }

    public Message respond(Conversation conversation, String contenido) {
        String respond = llm.speak(contenido);
        addMessage("Agent", respond, conversation);
        return new Message("Agent", respond);
    }
    
       public static String getFechaFormat(long fecha) {
        LocalDateTime temp = LocalDateTime.ofInstant(Instant.ofEpochSecond(fecha), ZoneOffset.UTC);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        formato = formato.withZone(ZoneId.systemDefault());
        formato = formato.withLocale(Locale.getDefault());
        return formato.format(temp);
    }

}
