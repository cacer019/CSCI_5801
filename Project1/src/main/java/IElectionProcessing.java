
import java.io.BufferedReader;

public interface IElectionProcessing {
    public boolean processElection(BufferedReader br);
    public String[] getCandidates();
    public String[] getParties();



}
