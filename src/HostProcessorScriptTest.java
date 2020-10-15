import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

class HostProcessorScriptTest {

    String filename = "H:\\Programación\\Clarity\\src\\input-file-10000.txt";
    String initDate = "01/08/2019";
    String endDate = "01/08/2020";
    String hostname = "Matina";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void mainCorrect() {

        HostProcessorScript.main(new String[]{filename, initDate, endDate, hostname});
    }

    @org.junit.jupiter.api.Test
    void mainDate() {

        InputStream in = new ByteArrayInputStream("01/08/2019".getBytes());
        System.setIn(in);

        HostProcessorScript.main(new String[]{filename, "fecha", endDate, hostname});
    }

    @org.junit.jupiter.api.Test
    void mainTimestamp() {
        initDate = "1565647204351";

        HostProcessorScript.main(new String[]{filename, initDate, endDate, hostname});
    }


}