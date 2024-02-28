package enric.examen.clases;

import java.time.LocalDate;

public class prueba {
    public static void main(String[] args) {
        LocalDate a = LocalDate.now();
        LocalDate b = a.plusDays(14);
        //b = b.minusYears(a.getYear());
        //b = b.minusMonths(a.getMonthValue());
        b = b.minusDays(14);


        System.out.println(b);
    }
}
