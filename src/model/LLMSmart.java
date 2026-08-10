package model;

import java.io.IOException;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;

public class LLMSmart implements ILLM {
    @Override
    public String speak(String frase) {
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        try {
            return ollamaAPI.ask("mistral", frase);
        } catch (OllamaBaseException | IOException | InterruptedException e) {
            System.err.println("Error al comunicarse con el servidor" + e.getMessage());
        }
        return null;

    }

    @Override
    public String getIdentifier() {

        return ("SmartLLM");
    }

}
