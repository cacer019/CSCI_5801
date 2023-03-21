
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
    }

    public void addBallot(Ballot nextBallot) {
       ballots.add(nextBallot);
    }

    @Override
    public int getBallotCount() {
        return 0;
    }

    @Override
    public String getParty() {
        return null;
    }

    public String getCandidateName() {
       return this.candidateName;
    }
}
