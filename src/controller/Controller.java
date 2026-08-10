package controller;

import java.util.ArrayList;
import java.util.List;
import model.Conversation;
import model.Message;
import model.Model;
import view.ApplicationView;

public class Controller {
    Model m;
    ApplicationView view;

    public Controller(Model m, ApplicationView view) {
        this.m = m;
        this.view = view;
        view.setController(this);
    };

    public void initApplication() {
        int temp = m.loadStatus();
        String initInfo = null;
        if (temp > 0) {
            initInfo = "\nSe han cargado " + temp + " Conversaciones del fichero bin\n";
        } else {
         
            initInfo = "\nParece ser que es la primera ejecucion del programa por lo que no se ha cargado ninguna conversación\n";
        }

        view.showApplicationStart(initInfo);
        view.showMainMenu();
        if (m.exportStatus()) {

            view.showApplicationEnd("\nEl estado de las conversaciones ha sido exportado con exito, saliendo del programa...\n");
        } else {

            view.showApplicationEnd("\nError al exportar las conversaciones, saliendo...\n");
        }
    }

    public List<Conversation> getConversations() {
        return m.getConversation();
    }

    public boolean elConversation(int i) {
        if (m.elConversation(i)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean importConversation() {
        return m.importConversation();
    }

    public boolean exportConversation() {
       return m.exportConversation();
    }

    public Conversation newConversation() {
        return m.newConversation();
    }

    public void addMessage(String emisor, String contenido, Conversation conversation) {
        m.addMessage(emisor, contenido, conversation);
    }

    public ArrayList<Message> getMessages() {
        return null;
    }

    public Message respond(Conversation conversation, String contenido) {
        return m.respond(conversation,contenido);
    }

}
