import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.Controller.Logs;

import java.io.File;
import java.io.IOException;
/**
 * Test de la class Logs
 *
 * @author Antoine R
 */
public class LogsTest {


    @Test
    void writeError() throws IOException {
        Logs testLog = new Logs();
        testLog.writeError("Cela na pas fonctionné");
        testLog.closeLogs();
    }

    @Test
    void writeInfo() throws IOException {
        Logs testLog = new Logs();
        testLog.writeInfo("Album not found");
        testLog.closeLogs();
    }


}
