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

        // Determine where total ballot # is located
        // Guaranteed lines are CPL identifier, # of parties, Parties, # of seats, and candidates
        int ballotNumLine = 5 + parties.length;

        // Skip to line before # of ballots are located in file using int lineSkip
        for(int i = 0; i < ballotNumLine; i++){
            br.readline();
        }

        // Step into line with ballot # & Store # of ballots
        int totalBallots = parseInt(br.readlineI());

        // Iterate through each line and look for ballot vote position
        for(int i = 0; i < totalBallots; i++){

            // Go char by char for each ballot make string 1st
            String currentBallot = br.readline();
            // Convert Ballot string into char array
            char[] ballotArray = currentBallot.toCharArray();

            // Iterate through the char array and distribute ballot
            for(int j = 0; j < ballotArray.length; j++){

                if(ballotArray[j] == ','){
                    continue;
                }

                else if(ballotArray[j] == '1'){
                    // Unsure here if we can do this, maybe we create setter in Party class to set ballot count
                    parties[j].ballotCount += 1;
                }
            }
        }
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
