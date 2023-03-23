import java.util.ArrayList;
public class Party implements IRepresentative {

    private ArrayList<String> candidates;
    private int numSeats;
    private String partyName;
    private int ballotCount;

    public Party(String party, ArrayList<String> candidateList) {
        partyName = party;
        candidates = candidateList;
    };

    public void addCandidate(String candidateName) {
        candidates.add(candidateName);
    }

    public void removeCandidate(String candidateToRemove) {
        candidates.remove(candidateToRemove);
    }

    public ArrayList<String> getCandidate() {
        return candidates;
    }

    @Override
    public int getBallotCount() {
        return ballotCount;
    }

    @Override
    public String getParty() {
        return partyName;
    }
}
