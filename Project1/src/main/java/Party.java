import java.util.ArrayList;
public class Party implements IRepresentative {

    private ArrayList<String> candidates;
    private int numSeats;
    private String partyName;
    private int ballotCount;

    public Party(String party, ArrayList<String> candidateList) {
        this.partyName = party;
        this.candidates = candidateList;
    };

    public void addCandidate(String candidateName) {
        this.candidates.add(candidateName);
    }

    public void removeCandidate(String candidateToRemove) {
        this.candidates.remove(candidateToRemove);
    }

    public ArrayList<String> getCandidate() {
        return this.candidates;
    }

    public int getNumSeats() {
        return this.numSeats;
    }

    public void setNumSeats(int seats) {
        this.numSeats = seats;
    }

    @Override
    public int getBallotCount() {
        return this.ballotCount;
    }

    public void setBallotCount(int numBallots) {
        this.ballotCount = numBallots;
    }

    @Override
    public String getParty() {
        return this.partyName;
    }
}
