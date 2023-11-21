package ra.academy.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FomatTime {
    public static String format(LocalDateTime time){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(time);
    }
}
