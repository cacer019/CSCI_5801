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
        // guaranteed lines are CPL identifier, # of parties, Parties, # of seats, and candidates
        int ballotNumLine = 5 + parties.length;

        // Skip to line before # of ballots are located in file using int lineSkip
        for(int i = 0; i < ballotNumLine; i++){
            br.readline();
        }

        // Step into line with ballot # & Store # of ballots
        int totalBallots = parseInt(br.readlineI());

        // Create array of ints, each position will refer to the # of ballots for the corresponding party
        int[] partyBallots = [parties.length];

        // Set all ballot counts to 0
        for(int i = 0; i < parties.length; i++){
            partyBallots[i] = 0;
        }

        // Iterate through each line and look for ballot vote position
        for(int i = 0; i < totalBallots; i++){

            // Go char by char in line and add to corresponding party index
            // All ballots have the the amount of characters as there are parties
            for(int j = 0; j < parties.length; j++){

                char current = br.read();

                // Check to see if valid read and not the end of line
                while(a != -1){

                    if(a == ','){
                        continue;
                    }

                    else if(a == '1'){
                        partyBallots[j] += 1;
                    }
                }
            }
        }

        // Update every Party object's ballotCount attribute to its distributed value
        for(int i = 0; int i < parties.length; i++){
            // I think we might need setter method for Party objects ballots due to info being private? Not sure myself.
            parties[i].ballotCount = partyBallots[i];
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
