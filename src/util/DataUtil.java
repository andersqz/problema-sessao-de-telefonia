
package util;


import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DataUtil {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String formatarDataHora(String iso) {
        if (iso == null || iso.isBlank()) {
            return "";
        }
        try {
            return Instant.parse(iso)
                    .atZone(ZoneId.of("America/Sao_Paulo"))   // horário de Brasília
                    .format(FORMATO);
        } catch (java.time.format.DateTimeParseException e) {
            return iso; // se vier fora do padrão, devolve como está
        }
    }
}