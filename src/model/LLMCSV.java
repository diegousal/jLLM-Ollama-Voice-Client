package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LLMCSV implements ILLM {

    @Override
    public String speak(String frase) {
        Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLM", "random.csv");
        String del = ",";
        List<String> lineas = null;
        ArrayList<Frase> frases = new ArrayList<>();

        try {
            lineas = Files.readAllLines(ruta);
            lineas.remove(0);
            for (String l : lineas) {
                if (l == null) {
                    lineas.remove(l);
                }
                Frase f = getFromDelimitedString(l, del);
                frases.add(f);
            }
        } catch (IOException e) {
            System.err.print("Error al leer el archivo" + e.getMessage());
            return null;
        }
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(lineas.size());

        if (frase.contains("hola") || frase.contains("Hola") || frase.contains("Buenas") || frase.contains("buenas")) {
            while (!frases.get(numeroAleatorio).getTipo().equals("saludo")) {
                numeroAleatorio = rand.nextInt(lineas.size());
            }
            return frases.get(numeroAleatorio).getContenido();
        }
        if (frase.contains("adios") || frase.contains("Adios") || frase.contains("hasta") || frase.contains("luego")) {
            while (!frases.get(numeroAleatorio).getTipo().equals("despedida")) {
                numeroAleatorio = rand.nextInt(lineas.size());
            }
            return frases.get(numeroAleatorio).getContenido();
        }
        if (frase.contains("?") || frase.contains("¿")) {
            while (!frases.get(numeroAleatorio).getTipo().equals("respond")) {
                numeroAleatorio = rand.nextInt(lineas.size());
            }
            return frases.get(numeroAleatorio).getContenido();
        }
        numeroAleatorio = rand.nextInt(lineas.size());
        return frases.get(numeroAleatorio).getContenido();
    }

    @Override
    public String getIdentifier() {
        return ("RandomCSVLLM");
    }

    public Frase getFromDelimitedString(String linea, String del) {
        String[] chunks = linea.split(del);
        int tam = chunks.length;
        if (tam < 3) {
            return null;
        }
        try {
            String tipo = chunks[0];
            int longitud = Integer.parseInt(chunks[1]);
            String contenido = chunks[2];
            int i = 3;
            while (tam > 3) {
                contenido = contenido + "," + chunks[i];
                i++;
                tam--;
            }
            return new Frase(tipo, longitud, contenido);

        } catch (Exception e) {
            System.err.println("Error al parsear la linea" + e.getMessage());
            return null;
        }

    }
}
