package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

public class JSONRepository implements IRepository {
    @Override
    public void exportConversations(List<Conversation> conversations) {
        Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLm", "output.json");
        File f = ruta.toFile();
           if (f.isFile() && f.exists()) {
        } else {
            System.err.println("El fichero no existe,creando...");
        }
        try {
            Gson gson = new Gson();
            String json = gson.toJson(conversations);
            Files.write(f.toPath(), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.err.println("Error al exportar:" + ex.getMessage());
        }
    }

    @Override
    public List<Conversation> importConversations() {
        Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLm", "output.json");
        File f = ruta.toFile();
        if (f.exists() && f.isFile()) {
            try {
                Gson gson = new Gson();
                String json = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
                Type tipoDeLista = new TypeToken<List<Conversation>>() {
                }.getType();
                return gson.fromJson(json, tipoDeLista);
            } catch (IOException ex) {
                System.err.println("Error:" + ex.getMessage());
            }
        }
        return null;
    }
}

