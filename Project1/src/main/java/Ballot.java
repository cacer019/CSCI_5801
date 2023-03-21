
import java.util.ArrayList;
public class Ballot {
    private int ballotIndex;
    private int numRankings;
    //private rankings
    private ArrayList<String> candidates;
    public Ballot(int ballotIndex, int numRankings, ArrayList<String> candidates) {
        this.ballotIndex = ballotIndex;
        this.numRankings = ballotIndex;
        this.candidates = candidates;
        //TODO: rankings replaced by curCandidate?
    }

    public String getNextCandidate() {
        return candidates.get(0);
    }

    public int getIndex() {
        return ballotIndex;
    }

    public void updateBallot() {
        candidates.remove(0);
    }

}
