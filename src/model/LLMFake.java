package model;

import java.util.Random;

public class LLMFake implements ILLM {

    @Override
    public String speak(String frase) {
        String[] respuesta = cargarFrases();
        
        if (frase.contains("hola")|| frase.contains("buenos días")|| frase.contains("buenas tardes")|| frase.contains("buenas noches")|| frase.contains("hey")|| frase.contains("que hay")|| frase.contains("que cuentas")|| frase.contains("que tal")|| frase.contains("que hay de nuevo")|| frase.contains("como estas")|| frase.contains("como va todo")|| frase.contains("como te encuentras")|| frase.contains("como has estado")|| frase.contains("como ha ido tu dia")|| frase.contains("como te ha ido")) {
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(10);
            return respuesta[numeroAleatorio];
        }
        if (frase.contains("mal") || frase.contains("triste") || frase.contains("enojado") || frase.contains("cansado") || frase.contains("estresado") || frase.contains("deprimido") || frase.contains("desanimado") || frase.contains("desesperado") || frase.contains("desilusionado") || frase.contains("desmotivado")) {
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(10, 20);
            return respuesta[numeroAleatorio];
        }
        if (frase.contains("adios") || frase.contains("hasta luego") || frase.contains("nos vemos pronto") || frase.contains("hasta pronto") || frase.contains("que tengas un buen dia")) {
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(20, 30);
            return respuesta[numeroAleatorio];
        }
        if (frase.contains("reir")|| frase.contains("chiste")|| frase.contains("broma")|| frase.contains("chistoso")|| frase.contains("gracioso")|| frase.contains("risa")|| frase.contains("jaja")|| frase.contains("jajaja")|| frase.contains("jajajaja")|| frase.contains("jajajajaja")) {
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(30, 40);
            return respuesta[numeroAleatorio];
        }
        if (frase.contains("ayuda") || frase.contains("consejo") || frase.contains("recomendacion") || frase.contains("sabiduria")  ) {
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(40, 50);
            return respuesta[numeroAleatorio];
        }

        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(50, 70);

        return respuesta[numeroAleatorio];
    }

    @Override
    public String getIdentifier() {
        return ("FakeLLM");
    }

    public String[] cargarFrases() {
        String[] mensajes = new String[70];

        mensajes[0] = "¡Hola! ¿Cómo estás?";
        mensajes[1] = "Buenos días, ¿cómo va todo?";
        mensajes[2] = "¡Hola! ¿Qué tal tu día?";
        mensajes[3] = "Hola, ¿cómo te encuentras?";
        mensajes[4] = "¡Buenas tardes! ¿Cómo has estado?";
        mensajes[5] = "Hola, ¿cómo ha ido tu día?";
        mensajes[6] = "¡Hola! ¿Qué cuentas?";
        mensajes[7] = "Buen día, ¿cómo te ha ido?";
        mensajes[8] = "¡Hola! ¿Qué hay de nuevo?";
        mensajes[9] = "Hey, ¿cómo estás?";

        // Ánimos
        mensajes[10] = "¡Tú puedes! Confía en ti mismo.";
        mensajes[11] = "Ánimo, cada día es una nueva oportunidad.";
        mensajes[12] = "No te rindas, sigue adelante.";
        mensajes[13] = "Eres capaz de lograr lo que te propongas.";
        mensajes[14] = "¡Sonríe! Hoy es un gran día.";
        mensajes[15] = "¡Siempre hay una luz al final del túnel!";
        mensajes[16] = "Cada paso cuenta, ¡sigue adelante!";
        mensajes[17] = "¡No te desanimes! Todo es posible.";
        mensajes[18] = "Recuerda que eres más fuerte de lo que piensas.";
        mensajes[19] = "¡Tienes todo para alcanzar tus metas!";

        // Despedidas
        mensajes[20] = "Hasta luego, ¡cuídate!";
        mensajes[21] = "Nos vemos pronto. ¡Hasta luego!";
        mensajes[22] = "¡Adiós! Que tengas un buen día.";
        mensajes[23] = "Ha sido genial hablar contigo. ¡Hasta pronto!";
        mensajes[24] = "¡Nos vemos! Que tengas un buen día.";
        mensajes[25] = "Adiós, ¡cuídate mucho!";
        mensajes[26] = "Hasta luego, ¡nos vemos pronto!";
        mensajes[27] = "¡Que tengas un excelente día! ¡Hasta luego!";
        mensajes[28] = "Nos vemos pronto. ¡Cuídate mucho!";
        mensajes[29] = "¡Adiós! Que tengas un buen día.";

        // Chistes
        mensajes[30] = "¿Por qué el libro de matemáticas estaba triste? Porque tenía muchos problemas.";
        mensajes[31] = "¿Cómo se llama un boomerang que no vuelve? Palo.";
        mensajes[32] = "¿Por qué el café no puede jugar al fútbol? Porque le teme a los tiros libres.";
        mensajes[33] = "¿Cuál es el colmo de un electricista? Tener mala corriente.";
        mensajes[34] = "¿Por qué los pájaros no usan Facebook? Porque ya tienen Twitter.";
        mensajes[35] = "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!";
        mensajes[36] = "¿Por qué el cuaderno de música fue al hospital? Porque tenía notas graves.";
        mensajes[37] = "¿Qué le dice un jardinero a otro? ¡Hola, cómo haces!";
        mensajes[38] = "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!";
        mensajes[39] = "¿Cómo se llama Superman cuando se va? Supermandarina.";

        // Refranes
        mensajes[40] = "Más vale tarde que nunca.";
        mensajes[41] = "No dejes para mañana lo que puedas hacer hoy.";
        mensajes[42] = "A quien madruga, Dios le ayuda.";
        mensajes[43] = "No hay mal que por bien no venga.";
        mensajes[44] = "La perseverancia es la clave del éxito.";
        mensajes[45] = "En boca cerrada no entran moscas.";
        mensajes[46] = "Quien siembra vientos, recoge tempestades.";
        mensajes[47] = "Más vale prevenir que lamentar.";
        mensajes[48] = "No hay peor sordo que el que no quiere oír.";
        mensajes[49] = "El que mucho abarca, poco aprieta.";
        // Otras frases
        mensajes[50] = "La vida es lo que pasa mientras estás ocupado haciendo otros planes.";
        mensajes[51] = "El conocimiento es poder.";
        mensajes[52] = "La paciencia es una virtud.";
        mensajes[53] = "El amor todo lo puede.";
        mensajes[54] = "La felicidad está en las pequeñas cosas.";
        mensajes[55] = "La creatividad es contagiosa, pásala.";
        mensajes[56] = "Vive y deja vivir.";
        mensajes[57] = "La vida es un viaje, no un destino.";
        mensajes[58] = "El éxito es la suma de pequeños esfuerzos repetidos día tras día.";
        mensajes[59] = "La única forma de hacer un gran trabajo es amar lo que haces.";
        mensajes[60] = "Nunca es tarde para ser lo que podrías haber sido.";
        mensajes[61] = "El optimismo es la fe que conduce al logro.";
        mensajes[62] = "La sonrisa es el idioma universal de la bondad.";
        mensajes[63] = "La verdadera sabiduría está en reconocer la propia ignorancia.";
        mensajes[64] = "No esperes por el momento perfecto, haz que el momento sea perfecto.";
        mensajes[65] = "El futuro pertenece a aquellos que creen en la belleza de sus sueños.";
        mensajes[66] = "Cada día es una nueva oportunidad para cambiar tu vida.";
        mensajes[67] = "Las oportunidades no ocurren, las creas.";
        mensajes[68] = "El cambio es inevitable, el crecimiento es opcional.";
        mensajes[69] = "La mente es como un paracaídas, solo funciona si se abre.";
        return mensajes;
    }

}
