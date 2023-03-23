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
        candidates.add(candidateName);
    }

    public void removeCandidate(String candidateToRemove) {
        candidates.remove(candidateToRemove);
    }

    public ArrayList<String> getCandidate() {
        return this.candidates;
    }

    @Override
    public int getBallotCount() {
        return this.ballotCount;
    }

    @Override
    public String getParty() {
        return this.partyName;
    }
}
