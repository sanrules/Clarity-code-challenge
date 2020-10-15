import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UnlimitedProcessorScript {
    static String filename = null;
    static long initTimestamp = 0L;
    static long endTimestamp = 0L;
    static String hostname = null;
    static String SEPARATOR = " ";
    static long HOUR = 60 * 60 * 1000;

    public static void main(String[] args) throws InterruptedException {
        hostname = args[0];
        filename = args[1];
        System.out.println(hostname);

        Date startHour = new Date();
        Date endHour = new Date();

        while (true) {
            //dormir una hora
            System.out.println("Start time: " + startHour);
            endHour.setTime(startHour.getTime() + HOUR);
            System.out.println("End time: " + endHour);

            Thread.sleep(HOUR);

            initTimestamp = startHour.getTime();
            System.out.println(initTimestamp);
            endTimestamp = endHour.getTime();
            System.out.println(endTimestamp);
            fileProcess();

            startHour = endHour;
        }
    }

    private static void fileProcess() {
        FileReader inputfile = null;
        try {
            inputfile = new FileReader(filename);

            BufferedReader bufferedReader = new BufferedReader(inputfile);

            String line;
            ArrayList<String> hostsConnected = new ArrayList<>();
            ArrayList<String> hostsReceived = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {

                String[] processedLine = line.split(SEPARATOR);
                long timestamp = Long.parseLong(processedLine[0]);
                String host1 = processedLine[1];
                String host2 = processedLine[2];

                if (timestamp >= initTimestamp && timestamp <= endTimestamp) {
                    if (host2.equalsIgnoreCase(hostname)) {
                        hostsReceived.add(host1);
                    } else if (host1.equalsIgnoreCase(hostname)) {
                        hostsConnected.add(host2);
                    }
                }
            }

            bufferedReader.close();
            inputfile.close();

            System.out.println("Hosts connected to " + hostname + ": " + hostsConnected.toString());
            System.out.println(hostname + " received connecton from: " + hostsReceived.toString());

            System.out.println("Total hosts connected to " + hostname + ": " + hostsConnected.size());
            System.out.println(hostname + " total connections received: " + hostsReceived.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
