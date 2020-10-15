import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnlimitedProcessorScriptTest {

    String filename = "H:\\Programación\\Clarity\\src\\hosts-log.log";

    @Test
    void main() throws InterruptedException {

        UnlimitedProcessorScript.main(new String[]{"Bridget", filename});

    }
}