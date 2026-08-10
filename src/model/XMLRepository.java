package model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLRepository implements IRepository {

    @Override
    public void exportConversations(List<Conversation> conversations) {
        Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLm", "output.xml");
        File f = ruta.toFile();
        if (f.isFile() && f.exists()) {
        } else {
            System.err.println("El fichero no existe,creando...");
        }
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(conversations);
            Files.write(f.toPath(), xml.getBytes(StandardCharsets.UTF_8));

        } catch (JsonProcessingException ex) {
            System.err.println("Error al exportar:" + ex.getMessage());

        } catch (IOException ex) {
            System.err.println("Error al exportra:" + ex.getMessage());
        }

    }

    @Override
    public List<Conversation> importConversations() {
        Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLm", "output.xml");
        File f = ruta.toFile();
        if (f.isFile() && f.exists()) {
            try {
                XmlMapper xmlMapper = new XmlMapper();
                String xml = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
                return xmlMapper.readValue(xml,
                        xmlMapper.getTypeFactory().constructCollectionType(List.class, Conversation.class));
            } catch (IOException ex) {

                System.err.println("Error al importar:" + ex.getMessage());
                return null;
            }
        }
        return null;

    }

}
