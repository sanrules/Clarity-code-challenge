import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

public class logGenerator {
    private final static String[] hosts = {
            "Jeannette",
            "Fredia",
            "Angella",
            "Sebastian",
            "Jin",
            "Ruthe",
            "Jerold",
            "Hershel",
            "Rosalva",
            "Erik",
            "Claude",
            "Camilla",
            "Noemi",
            "Sheron",
            "Lydia",
            "Elfrieda",
            "Bridget",
            "Eddy",
            "Tena",
            "Frankie",
            "Lucien",
            "Kurt",
            "Jann",
            "Bailey",
            "Paulette",
            "Shiloh",
            "Salina",
            "Debbi",
            "Angelica",
            "Sha",
            "Tien",
            "Robt",
            "Betty",
            "Eliz",
            "Ellamae",
            "Ethyl",
            "Veda",
            "Rosalyn",
            "Roselle",
            "Madonna",
            "Nada",
            "Stacee",
            "Cindi",
            "Nola",
            "Enoch",
            "Pamelia",
            "Lacresha",
            "Christinia",
            "Arnette",
            "Idalia"
    };

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        File logFile = new File("H:\\Programación\\Clarity\\src\\hosts-log.log");
        PrintStream stream = new PrintStream(logFile);

        while (true) {
            System.setOut(stream);
            System.out.println(LocalDate.now().toEpochSecond(LocalTime.now(), ZoneOffset.UTC) * 1000 + " " + hosts[(int) (Math.random() * hosts.length)] + " " + hosts[(int) (Math.random() * hosts.length)]);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
