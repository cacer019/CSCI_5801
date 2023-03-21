import java.io.BufferedReader;

public class IRProcessing implements IElectionProcessing {
    @Override
    public boolean processElection(BufferedReader br) {
        return false;
    }

    @Override
    public String[] getCandidates() {
        return new String[0];
    }

    @Override
    public String[] getParties() {
        return new String[0];
    }
}
