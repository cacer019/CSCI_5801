/**
 * CPLProcessingTest.java is used for testing the methods in the CPLProcessing class.
 *
 * @author abouz009 and berg00063.
 */

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CPLProcessingTest class contains methods for testing the CPLProcessing class.
 */
class CPLProcessingTest {

    /**
     * Tests the distributeSeats() method in the CPLProcessing class to make sure the
     * correct number of seats are allocated to the correct parties.
     * @throws IOException  if IO exception occurs when reading from csv file.
     */
    @Test
    void distributeSeats() throws IOException {
        FileReader csvFile = new FileReader("src/test/java/CPLTesting.csv");

        BufferedReader br = new BufferedReader(csvFile);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CPLProcessing election = new CPLProcessing(br);

        assertEquals(election.getParties().get(0).getNumSeats(), 1);
        assertEquals(election.getParties().get(1).getNumSeats(), 1);
        assertEquals(election.getParties().get(2).getNumSeats(), 0);
        assertEquals(election.getParties().get(3).getNumSeats(), 1);
        assertEquals(election.getParties().get(4).getNumSeats(), 0);
        assertEquals(election.getParties().get(5).getNumSeats(), 0);
    }

    /**
     * Tests the setParties() method in the CPLProcessing class to make sure
     * a party is created with the correct party name and candidates associated with that party .
     * @throws an IOException if an IO error occurs when reading from file
     */
    @Test
    void setParties() throws IOException {
        FileReader csvFile = new FileReader("src/test/java/CPLTesting.csv");

        BufferedReader br = new BufferedReader(csvFile);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CPLProcessing election = new CPLProcessing(br);     //setParties() called

        ArrayList<Party> parts = election.getParties();     //check if parties are all correct
        ArrayList<String> cands = new ArrayList<>();

        cands.add("Foster");
        cands.add("Volz");
        cands.add("Pike");
        assertEquals(parts.get(0).getParty(), "Democratic");
        assertEquals(parts.get(0).getCandidates(), cands);

        cands.clear();
        cands.add("Green");
        cands.add("Xu");
        cands.add("Wang");
        assertEquals(parts.get(1).getParty(), "Republican");
        assertEquals(parts.get(1).getCandidates(), cands);

        cands.clear();
        cands.add("Jacks");
        cands.add("Rosen");
        assertEquals(parts.get(2).getParty(), "New Wave");
        assertEquals(parts.get(2).getCandidates(), cands);

        cands.clear();
        cands.add("McClure");
        cands.add("Berg");
        assertEquals(parts.get(3).getParty(), "Reform");
        assertEquals(parts.get(3).getCandidates(), cands);

        cands.clear();
        cands.add("Zheng");
        cands.add("Melvin");
        assertEquals(parts.get(4).getParty(), "Green");
        assertEquals(parts.get(4).getCandidates(), cands);

        cands.clear();
        cands.add("Peters");
        assertEquals(parts.get(5).getParty(), "Independent");
        assertEquals(parts.get(5).getCandidates(), cands);
    }

    /**
     * Tests the distributeBallots() method in the CPLProcessing class to check that the
     * ballots are allocated correctly to their respective parties.
     * @throws IOException  if IO exception occurs when reading from csv file.
     */
    @Test
    void distributeBallots() throws IOException {
        FileReader csvFile = new FileReader("src/test/java/CPLTesting.csv");

        BufferedReader br = new BufferedReader(csvFile);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CPLProcessing election = new CPLProcessing(br);

        assertEquals(election.getParties().get(0).getBallotCount(), 3);
        assertEquals(election.getParties().get(1).getBallotCount(), 2);
        assertEquals(election.getParties().get(2).getBallotCount(), 0);
        assertEquals(election.getParties().get(3).getBallotCount(), 2);
        assertEquals(election.getParties().get(4).getBallotCount(), 1);
        assertEquals(election.getParties().get(5).getBallotCount(), 1);
    }

    /**
     * Tests the processElection() method in the CPLProcessing class to check that
     * the CPL Election results are correct.
     * @throws IOException  if IO exception occurs when reading from csv file.
     */
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

    /**
     * Tests the getCandidates() method in the CPLProcessing class to make sure all
     * candidates are extracted and put into a list.
     * @throws IOException  if IO exception occurs when reading from csv file.
     */
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

    /**
     * Tests the getParties() method in the CPLProcessing class to make sure all
     * parties are extracted and put into a list.
     * @throws IOException  if IO exception occurs when reading from csv file.
     */
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

        ArrayList<Party> parties = election.getParties();
        assertEquals("Democratic", parties.get(0).getParty());
        assertEquals("Republican", parties.get(1).getParty());
        assertEquals("New Wave", parties.get(2).getParty());
        assertEquals("Reform", parties.get(3).getParty());
        assertEquals("Green", parties.get(4).getParty());
        assertEquals("Independent", parties.get(5).getParty());
    }
}