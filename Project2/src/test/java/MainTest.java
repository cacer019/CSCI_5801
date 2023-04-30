/**
 * MainTest.java is used for testing the functionality of main
 *
 * @author tracy255, Caleb Tracy.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MainTest class contains test methods for the main method.
 */
public class MainTest {

    /**
     * A test to make sure the program can handle the input of multiple files.
     */
    @Test
    void getFiles() {
        assertDoesNotThrow(() -> main.main(new String[]{"IRTesting1.csv", "IRTesting2.csv"}));
    }
}
