import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IRProcessingTest {

    BufferedReader br;

    @Test
    void processElection() throws FileNotFoundException {
        //Simulate main giving a br to IRProcessing
//        File file = new File(".");
//        for(String fileNames : file.list()) System.out.println(fileNames);
        FileReader csvFile = new FileReader("src/test/java/IRTesting1.csv");

        br = new BufferedReader(csvFile);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IRProcessing election = new IRProcessing(br);

        String[] cands = election.getCandidates();
        assertEquals("Rosen", cands[0]);
        assertEquals("Kleinberg", cands[1]);
        assertEquals("Chou", cands[2]);
        assertEquals("Royce", cands[3]);

        ArrayList<Candidate> curCandsArray = election.getCandidateArray();
        assertEquals(curCandsArray.get(0).getCandidateName(), "Rosen");
        assertEquals(curCandsArray.get(2).getCandidateName(), "Chou");
        assertEquals(curCandsArray.get(0).getBallotCount(), 5);
        assertEquals(curCandsArray.get(2).getBallotCount(), 1);

    }
//
//    @Test
//    void setCandidates() {
//
//    }
//
//    @Test
//    void getCandidates() {
//
//    }
//
//    @Test
//    void getParties() {
//    }
//
//    @Test
//    void determineLoser() {
//    }
//
//    @Test
//    void redistributeBallots() {
//
//    }
}