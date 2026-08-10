package view;

import static com.coti.tools.Esdia.*;
import java.io.IOException;
import java.util.List;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngine;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngineNative;
import io.github.jonelo.jAdapterForNativeTTS.engines.Voice;
import io.github.jonelo.jAdapterForNativeTTS.engines.VoicePreferences;
import io.github.jonelo.jAdapterForNativeTTS.engines.exceptions.SpeechEngineCreationException;
import model.Conversation;
import model.Message;

public class VoiceConsoleView extends ApplicationView {

    @Override
    public void showApplicationStart(String initInfo) {
        System.out.println(initInfo);
    }

    @Override
    public void showMainMenu() {
        int opcion;
        do {
            speak("Menu con Voz.\nIntroduzca la opcion que desee\n1 Nueva conversacion.\n2 Ir al menu CRUD conversacion.\n3 Ir al menu de exportacion.\n4 Salir.");
            System.err.println("     Menu Voice Console View");
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
                case 4:
                    break;
                default:
                    System.out.println("Opcion incorrecta,introduzca una opcion valida");

            }
        } while (opcion != 4);

    }

    public void newConvesation() {
        int i = 0;
        Conversation conversation = c.newConversation();
          speak("Ha elegido iniciar una nueva conversacion\nIntroduzca su nombre");
        System.out.println("----------------------------------------------------------------");
        System.out.println("|Usted esta en el modo conversacion,para salir escriba \"/salir\"|");
        System.out.println("----------------------------------------------------------------");
        String emisor = readString_ne("Introduzca su nombre para empezar la conversacion: ");
        System.out.println("----------------------------------------------------------------\n");
        String contenido = readString_ne("Introduzca su Mensaje: ");
        while (!contenido.equals("/salir")) {
            c.addMessage(emisor, contenido, conversation);
            Message Message = c.respond(conversation, contenido);
            for (; i < conversation.getMessages().size() - 1; i++) {
                System.out.println(conversation.getMessages().get(i).getAsString());
            }
            System.out.println(Message.getAsString());
            contenido = readString_ne("Introduzca su Mensaje: ");
        }
    }

    public void CRUDmenu() {
        int opcion;
        do {
            speak("Ha elegido entrar en el Menu CRUD.\nIntroduzca la opcion que desee\n1 Listar conversaciones.\n2 Eliminar conversacion.\n3 Salir.");
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
            int opcion = readInt(
                    "\nIntroduzca la conversacion a eliminar (Puede introducir -1 para salir y no eliminar ninguna conversacion): ",
                    -1, i - 1);
            if (opcion == -1) {
                return;
            }
            if (c.elConversation(opcion)) {
                speak("Conversacion eliminada con exito");
                System.out.println("Conversacion eliminada con exito");
            } else {
                speak("Error al eliminar la conversacion");
                System.err.println("Error al eliminar la conversacion");
            }
        }
    }

    public void ImpExpMenu() {
        int opcion;
        do {
            speak("Ha elegido Menu de exportacion e importacion.\nIntroduzca la opcion que desee.\n1 Importar conversaciones.\n2 Exportar conversaciones.\n3 Salir.");
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
                        System.err.println("Error al importar las conversaciones");
                    }else{
                        System.out.println("\nConversaciones importadas con exito");}
                    break;
                case 2:
                    if (!c.exportConversation()) {
                        System.err.println("Error al exportar las conversaciones");

                    }else {
                        System.out.println("\nConversaciones exportadas con exito");
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

    public void speak(String text) {
        try {
            SpeechEngine speechEngine = SpeechEngineNative.getInstance();
            List<Voice> voices = speechEngine.getAvailableVoices();

            // We want to find a voice according our preferences
            VoicePreferences voicePreferences = new VoicePreferences();
            voicePreferences.setLanguage("es"); // ISO-639-1
            voicePreferences.setCountry("ES"); // ISO 3166-1 Alpha-2 code
            voicePreferences.setGender(VoicePreferences.Gender.FEMALE);
            Voice voice = speechEngine.findVoiceByPreferences(voicePreferences);

            // simple fallback just in case our preferences didn't match any voice
            if (voice == null) {
                System.out.printf("Warning: Voice has not been found by the voice preferences %s%n", voicePreferences);
                voice = voices.get(0); // it is guaranteed that the speechEngine supports at least one voice
                System.out.printf("Using \"%s\" instead.%n", voice);
            }

            speechEngine.setVoice(voice.getName());
            speechEngine.say(text);

        } catch (SpeechEngineCreationException | IOException e) {
            System.err.println(e.getMessage());
        }

    }

}
