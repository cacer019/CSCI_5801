import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {
    @Test
    void addCandidate() {
    ArrayList<String> candidates = new ArrayList<>();
    candidates.add("candidate1");

    Party party = new Party("party", candidates);
    party.addCandidate("CANDIDATE2");
    party.addCandidate("cAnDiDaTe3");
    party.addCandidate("CANDIdate4");
    party.addCandidate("candiDATE5");

    assertEquals(party.getCandidates().get(0), "candidate1");
    assertEquals(party.getCandidates().get(1), "CANDIDATE2");
    assertEquals(party.getCandidates().get(2), "cAnDiDaTe3");
    assertEquals(party.getCandidates().get(3), "CANDIdate4");
    assertEquals(party.getCandidates().get(4), "candiDATE5");

    }

    @Test
    void removeCandidate() {
        ArrayList<String> candidates = new ArrayList<>();
        candidates.add("candidate1");

        Party party = new Party("party", candidates);
        party.addCandidate("CANDIDATE2");
        party.addCandidate("cAnDiDaTe3");

        party.removeCandidate("CANDIDATE2");
        assertEquals(party.getCandidates().get(1), "cAnDiDaTe3");
        party.removeCandidate("candidate1");
        assertEquals(party.getCandidates().get(0), "cAnDiDaTe3");
        party.removeCandidate("cAnDiDaTe3");
        assertEquals(party.getCandidates().size(), 0);

    }

    @Test
    void getCandidates() {
    }

    @Test
    void getNumSeats() {

    }

    @Test
    void setNumSeats() {

    }

    @Test
    void getBallotCount() {
    }

    @Test
    void setBallotCount() {
    }

    @Test
    void getParty() {
        ArrayList<String> candidates = new ArrayList<>();
        candidates.add("candidate1");
        Party party1 = new Party("Republican", candidates);
        Party party2 = new Party("democratic", candidates);
        Party party3 = new Party("iNdEpEnDeNt", candidates);
        Party party4 = new Party("REAList", candidates);

        assertEquals(party1.getParty(), "Republican");
        assertEquals(party2.getParty(), "democratic");
        assertEquals(party3.getParty(), "iNdEpEnDeNt");
        assertEquals(party4.getParty(), "REAList");
    }
}