package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FormatUtils {

    // Método para formatar TimeStamp para String no formato "dd/MM/yyyy HH:mm"
    public static String formatTimestamp(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(timestamp);
    }

    // Método para formatar telefone para o formato "(00)00000-0000"
    public static String formatPhoneNumber(String phoneNumber) {
        // Remove todos os caracteres não numéricos
        phoneNumber = phoneNumber.replaceAll("\\D", "");

        // Verifica se o número tem 10 ou 11 dígitos
        if (phoneNumber.length() == 10) {
            // Formato: (00)0000-0000
            return String.format("(%s)%s-%s",
                phoneNumber.substring(0, 2),
                phoneNumber.substring(2, 6),
                phoneNumber.substring(6, 10));
        } else if (phoneNumber.length() == 11) {
            // Formato: (00)00000-0000
            return String.format("(%s)%s-%s",
                phoneNumber.substring(0, 2),
                phoneNumber.substring(2, 7),
                phoneNumber.substring(7, 11));
        } else {
            // Retorna o número original se não tiver o tamanho esperado
            return phoneNumber;
        }
    }
}
