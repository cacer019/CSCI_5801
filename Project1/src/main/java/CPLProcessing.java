import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CPLProcessing implements IElectionProcessing {

    private ArrayList<Party> parties;

    private void distributeSeats(BufferedReader br) {

    }

    private void setParties(BufferedReader br) throws IOException {
        // Gets number if parties (2nd Line)
        int numParties = Integer.parseInt(br.readLine());
        // Gets list of party names (3rd line)
        String curLine = br.readLine();
        String[] partyNames = curLine.split(",");
        // Reads candidates of each party and creates a Party object with respective party name and candidates
        for (int i = 0; i < numParties; i++) {
            curLine = br.readLine();
            ArrayList<String> candidatesOfParty = new ArrayList<>(Arrays.asList(curLine.split(";")));
            Party party = new Party(partyNames[i], candidatesOfParty);
            this.parties.add(party);
        }
    }

    private void distributeBallots(BufferedReader br, Party[] parties) {

    }
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
