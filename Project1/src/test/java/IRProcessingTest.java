import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IRProcessingTest {

    Ballot temp1 = new Ballot(0, 0, null);
    Ballot temp2 = new Ballot(0, 0, null);
    @Test
    void processElection() {
        assertEquals( temp1, temp2);

    }

    @Test
    void getCandidates() {
        assertEquals( 1, 1);
    }
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
//    }
}