/**
 * IRProcessingTest.java is used for testing the methods in the IRProcessing class.
 *
 * Written by tracy255.
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * IRProcessingTest class contains methods for testing the IRProcessing class.
 */
class IRProcessingTest {

    /**
     * Tests the processElection(), getCandidates(), setCandidates(), and
     * getCandidateArray() methods in the IRProcessing class.
     * The IRProcessing constructor calls setCandidate(), so we test that the candidates
     * in the IRProcessing instance were created properly, and therefore test setCandidate().
     * @throws IOException  if IO exception occurs when reading from csv file.
     */
    @Test
    void processElection1() throws IOException {
        //Simulate main giving a br to IRProcessing
        FileReader csvFile = new FileReader("src/test/java/IRTesting1.csv");

        BufferedReader br = new BufferedReader(csvFile);
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

        assertEquals("Rosen", election.processElection());

    }

    /**
     * Tests the processElection() method in the IRProcessing class.
     * @throws IOException  if IO exception occurs when reading from csv file.
     */
    @Test
    void processElection2() throws IOException {
        //Simulate main giving a br to IRProcessing
        FileReader csvFile = new FileReader("src/test/java/IRTesting2.csv");

        BufferedReader br = new BufferedReader(csvFile);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IRProcessing election = new IRProcessing(br);

        String[] cands = election.getCandidates();
        assertEquals("Rosen", cands[0]);
        assertEquals("Kleinberg", cands[1]);

        ArrayList<Candidate> curCandsArray = election.getCandidateArray();
        assertEquals("Rosen", curCandsArray.get(0).getCandidateName());
        assertEquals("Kleinberg", curCandsArray.get(1).getCandidateName());
        assertEquals(3, curCandsArray.get(0).getBallotCount());
        assertEquals(4, curCandsArray.get(1).getBallotCount());

        //The tie in the first round can go royce or joe, but klein still wins
        assertEquals("Kleinberg", election.processElection());

    }

//    /**
//     * Tests the setCandidates() method in the IRProcessing class.
//     */
//    @Test
//    void setCandidates() {
//
//    }
//
//    /**
//     * Tests the getCandidates() method in the IRProcessing class.
//     */
//    @Test
//    void getCandidates() {
//
//    }
//    /**
//     * Tests the getParties() method in the IRProcessing class.
//     */
//    @Test
//    void getParties() {
//    }
//
//    /**
//     * Tests the determineLoser() method in the IRProcessing class.
//     */
//    @Test
//    void determineLoser() throws IOException{
//        FileReader csvFile = new FileReader("src/test/java/IRTesting4.csv");
//
//        BufferedReader br = new BufferedReader(csvFile);
//        try {
//            br.readLine();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        IRProcessing election = new IRProcessing(br);

//        Candidate loser;
//        loser = election.determineLoser();
//        assertEquals(loser.getCandidateName(),"bob");
//        election.redistributeBallots(loser);

//        loser = election.determineLoser();
//        assertEquals(loser.getCandidateName(),"elias");
//        election.redistributeBallots(loser);
//
//        loser = election.determineLoser();
//        assertEquals(loser.getCandidateName(),"garrett");
//        election.redistributeBallots(loser);
//
//        loser = election.determineLoser();
//        assertEquals(loser.getCandidateName(),"garrett");
//
//
//
//
//    }
//
//    /**
//     * Tests the redistributeBallots() method in the IRProcessing class.
//     */
//    @Test
//    void redistributeBallots() throws IOException{
//        FileReader csvFile = new FileReader("src/test/java/IRTesting3.csv");
//
//        BufferedReader br = new BufferedReader(csvFile);
//        try {
//            br.readLine();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        IRProcessing election = new IRProcessing(br);
//
//        String[] cands1 = election.getCandidates();
//        assertEquals(cands[0].getBallotCount(), 4);
//        assertEquals(cands[1].getBallotCount(), 2);
//        assertEquals(cands[2].getBallotCount(), 3);
//
//
//        election.redistributeBallots(election.determineLoser());
//        String[] cands2 = election.getCandidates();
//        assertEquals(cands[0].getBallotCount(), 5);
//        assertEquals(cands[1].getBallotCount(), 4);
//    }
}