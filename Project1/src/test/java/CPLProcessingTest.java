import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CPLProcessingTest {
    @Test
    void distributeSeats() {
    }

    @Test
    void setParty() {
    }

    @Test
    void distributeBallots() {
    }

    @Test
    void processElection() throws IOException {
        FileReader csvFile = new FileReader("src/test/java/CPLTesting.csv");

        BufferedReader br = new BufferedReader(csvFile);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CPLProcessing election = new CPLProcessing(br);

        assertEquals("Foster,Green,McClure,", election.processElection());

    }

    @Test
    void getCandidates() throws IOException {
        FileReader csvFile = new FileReader("src/test/java/CPLTesting.csv");

        BufferedReader br = new BufferedReader(csvFile);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CPLProcessing election = new CPLProcessing(br);


        String[] candidates = election.getCandidates();

        assertEquals("Foster", candidates[0]);
        assertEquals("Volz", candidates[1]);
        assertEquals("Pike", candidates[2]);
        assertEquals("Green", candidates[3]);
        assertEquals("Xu", candidates[4]);
        assertEquals("Wang", candidates[5]);
        assertEquals("Jacks", candidates[6]);
        assertEquals("Rosen", candidates[7]);
        assertEquals("McClure", candidates[8]);
        assertEquals("Berg", candidates[9]);
        assertEquals("Zheng", candidates[10]);
        assertEquals("Melvin", candidates[11]);
        assertEquals("Peters", candidates[12]);
    }

    @Test
    void getParties() throws IOException {
        FileReader csvFile = new FileReader("src/test/java/CPLTesting.csv");

        BufferedReader br = new BufferedReader(csvFile);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CPLProcessing election = new CPLProcessing(br);

        String[] parties = election.getParties();
        assertEquals("Democratic", parties[0]);
        assertEquals("Republican", parties[1]);
        assertEquals("New Wave", parties[2]);
        assertEquals("Reform", parties[3]);
        assertEquals("Green", parties[4]);
        assertEquals("Independent", parties[5]);
    }
}