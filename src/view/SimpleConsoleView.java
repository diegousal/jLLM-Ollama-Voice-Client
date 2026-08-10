package view;

import static com.coti.tools.Esdia.*;
import java.util.List;
import model.Conversation;
import model.Message;

public class SimpleConsoleView extends ApplicationView {

    @Override
    public void showApplicationStart(String initInfo) {
        System.out.println(initInfo);
    }

    @Override
    public void showMainMenu() {
        int opcion;
        do {
            System.err.println("     Menu Simple Console View");
            System.out.println("--------------------------------");
            System.out.println("1) Nueva  conversacion");
            System.out.println("2) Ir al menu CRUD conversacion ");
            System.out.println("3) Ir al menu de exportacion ");
            System.out.println("4) Salir ");
            System.out.println("--------------------------------");

            opcion = readInt("Introduzca la opcion que desee: ");

            switch (opcion) {
                case 1:
                    newConvesation();

                    break;
                case 2:
                    CRUDmenu();

                    break;
                case 3:
                    ImpExpMenu();

                    break;

            }
        } while (opcion != 4);

    }

    public void newConvesation() {
        int i = 0;
        Conversation conversation = c.newConversation();
                    System.out.println("----------------------------------------------------------------");
        System.out.println("|Usted esta en el modo conversacion,para salir escriba \"/salir\"|");
                    System.out.println("----------------------------------------------------------------");

        String emisor = readString_ne("Introduzca su nombre para empezar la conversacion: ");
               System.out.println("----------------------------------------------------------------\n");


        String contenido = readString_ne("Introduzca su Mensaje: ");

        while (!contenido.equals("/salir")) {
            c.addMessage(emisor, contenido, conversation);
            Message Message = c.respond(conversation, contenido);
            for (; i < conversation.getMessages().size()-1; i++) {
                System.out.println(conversation.getMessages().get(i).getAsString());
            }
            System.out.println(Message.getAsString());
            contenido = readString_ne("Introduzca su Mensaje: ");
        }

    }

    public void CRUDmenu() {
        int opcion;
        do {
            System.out.println("\n            Menu CRUD");
            System.out.println("--------------------------------");
            System.out.println("1) Listar conversaciones");
            System.out.println("2) Eliminar conversacion");
            System.out.println("3) Salir ");
            System.out.println("--------------------------------");
            opcion = readInt("Introduzca la opcion que desee: ");

            switch (opcion) {
                case 1:
                    listConversations();

                    break;
                case 2:
                    elConversation();

                    break;
                case 3:
                    System.out.println("Saliendo...");

            }
        } while (opcion != 3);

    }

    public void elConversation() {
        int i = listConversations();
        if (i >= 0) {
            int opcion = readInt("\nIntroduzca la conversacion a eliminar: ", 0, i - 1);
            if (c.elConversation(opcion)) {
                System.out.println("Conversacion eliminada con exito");
            } else {
                System.err.println("Error al eliminar la conversacion");
            }
        }
    }

    public void ImpExpMenu() {
        int opcion;
        do {
                        System.out.println("\n  Menu Exportacion/Importacion");
            System.out.println("--------------------------------");
            System.out.println("1) Importar conversaciones");
            System.out.println("2) Exportar conversaciones");
            System.out.println("3) Salir ");
            System.out.println("--------------------------------");
            opcion = readInt("Introduzca la opcion que desee: ");

            switch (opcion) {
                case 1:
                    if (!c.importConversation()) {
                        System.err.println("\nError al importar las conversaciones");
                    }else{
                        System.out.println("\nConversaciones importadas con exito");
                    }
                    break;
                case 2:
                    if (!c.exportConversation()) {
                        System.err.println("\nError al exportar las conversaciones");

                    }else {
                        System.out.println("Conversaciones exportadas con exito");
                    }

                    break;
                case 3:
                    System.out.println("Saliendo...");

            }
        } while (opcion != 3);

    }

    public int listConversations() {
        int i = 0;
        List<Conversation> conversations = c.getConversations();
        if (conversations.size() > 0) {

            for (Conversation conversation : conversations) {
                System.out.printf("%d. " + conversation.getFechaFormat(conversation.getFechaIni()) + " | "
                        + conversation.getNumeroMessages() + " | "
                        + conversation.getStartOfMessage() + "\n", i++);
            }
            if (yesOrNo("Desea visualizar el contenido de alguna conversacion? ")) {

                int opcion = readInt("Introduzca la conversacion a visualizar: ", 0, i - 1);
                System.out.println("\nConversacion del: "
                        + conversations.get(opcion).getFechaFormat(conversations.get(opcion).getFechaIni()));
                List<Message> temp = conversations.get(opcion).getMessages();
                for (Message Message : temp) {
                    System.out.println(Message.getAsString());
                }
            }
            return i;
        } else {
            System.out.println("Todavia no hay conversaciones");
            return -1;
        }

    }

    @Override
    public void showApplicationEnd(String endInfo) {
        System.out.println(endInfo);
    }
}
