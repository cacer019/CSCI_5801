
import java.util.ArrayList;
public class Ballot {
    private int ballotIndex;  //starts at 0
    private int numRankings; //will always start with at least 1
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

    public boolean updateBallot() {
        candidates.remove(0);
        this.numRankings--;
        if (this.numRankings >= 0) {
            return false;
        }
        else return true;
    }

}
