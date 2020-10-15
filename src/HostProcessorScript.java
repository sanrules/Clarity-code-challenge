import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * 1. Parse the data with a time_init, time_end
 * Build a tool, that given the name of a file (with the format described above), an init_datetime, an
 * end_datetime, and a Hostname, returns:
 * a list of hostnames connected to the given host during the given period
 */
public class HostProcessorScript {

    static String filename = null;
    static String initDatetime = null;
    static long initTimestamp = 0L;
    static String endDatetime = null;
    static long endTimestamp = 0L;
    static String hostname = null;
    static Scanner scanner = null;
    static String DATE_FORMAT = "dd/MM/yyyy";
    static String SEPARATOR = " ";

    private static void fileProcess() {
        FileReader inputfile = null;
        try {
            inputfile = new FileReader(filename);

            BufferedReader bufferedReader = new BufferedReader(inputfile);

            String line;
            int totalHosts = 0;

            while ((line = bufferedReader.readLine()) != null) {

                String[] processedLine = line.split(SEPARATOR);
                long timestamp = Long.parseLong(processedLine[0]);
                String host1 = processedLine[1];
                String host2 = processedLine[2];

                if (timestamp > endTimestamp) {
                    break;
                }

                if (timestamp >= initTimestamp && timestamp <= endTimestamp) {
                    if (host2.equalsIgnoreCase(hostname)) {
                        System.out.println(host1);
                        totalHosts++;
                    }
                }
            }

            bufferedReader.close();
            inputfile.close();

            System.out.println("Total hosts connected to " + hostname + ": " + totalHosts);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setFichero() {
        if (filename.isEmpty()) {
            System.out.println("Name of the file");
            filename = scanner.next();
        }
        if (!new File(filename).exists()) {
            filename = "";
            setFichero();
        }
    }

    private static void setInitDate() {

        if (initDatetime.isEmpty()) {
            System.out.println("Init datetime. Format required: " + DATE_FORMAT);
            initDatetime = scanner.next();
        }

        try {
            initTimestamp = setUnixTimestamp(initDatetime);
        } catch (Exception e) {
            System.out.println("Date format error. Format required: " + DATE_FORMAT);
            initDatetime = "";
            setInitDate();
        }
    }

    private static void setEndDate() {
        if (endDatetime.isEmpty()) {
            System.out.println("End datetime. Format required: " + DATE_FORMAT);
            endDatetime = scanner.next();
        }
        try {
            endTimestamp = setUnixTimestamp(endDatetime);
            if (endTimestamp < initTimestamp) {
                System.out.println(" End datetime cannot be earlier than init datetime");
                setEndDate();
            }
        } catch (Exception e) {
            System.out.println("Date format error. Format required: " + DATE_FORMAT);
            endDatetime = "";
            setEndDate();
        }
    }

    private static void setHostname() {
        if (hostname.isEmpty()) {
            System.out.println("Hostname");
            hostname = scanner.next();
        }
    }

    private static long setUnixTimestamp(String datetime) {
        LocalDate date = LocalDate.parse(
                datetime,
                DateTimeFormatter.ofPattern(DATE_FORMAT)
        );

        return date.toEpochSecond(LocalTime.now(), ZoneOffset.UTC) * 1000;
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        if (args.length != 0) {
            filename = args[0];
            initDatetime = args[1];
            endDatetime = args[2];
            hostname = args[3];
        }

        setFichero();
        setInitDate();
        setEndDate();
        setHostname();

        fileProcess();
    }
}
