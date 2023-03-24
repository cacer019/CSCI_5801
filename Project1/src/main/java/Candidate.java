
import java.util.ArrayList;

public class Candidate implements  IRepresentative {

    private int ballotCount;
    private String partyName;
    private String candidateName;
    //private ballots
    private ArrayList<Ballot> ballots;

   public Candidate(String partyName, String candidateName) {
       this.partyName = partyName;
       this.candidateName = candidateName;
   }

    public void removeBallot(int index) {
       ballots.remove(index);
       this.ballotCount--;
    }

    public void addBallot(Ballot nextBallot) {
       this.ballots.add(nextBallot);
       this.ballotCount++;
    }

    @Override
    public int getBallotCount() { return this.ballotCount; }

    @Override
    public String getParty() {
        return this.partyName;
    }

    public ArrayList<Ballot> getBallots() { return this.ballots; }

    public String getCandidateName() {
       return this.candidateName;
    }
}
