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
        int ballotNumLine = 5 + parties.length;

        // Skip to line before # of ballots are located in file using int lineSkip
        for(int i = 0; i < ballotNumLine; i++){
            br.readline();
        }

        // Step into line with ballot # & Store # of ballots
        int totalBallots = parseInt(br.readlineI());

        // create array of ints, each position will refer to the # of ballots for the corresponding party
        int[] partyBallots = [parties.length];

        // set all ballot counts to 0
        for(int i = 0; i < parties.length; i++){
            partyBallots[i] = 0;
        }

        // iterate through each line and look for ballot vote position
        for(int i = 0; i < totalBallots; i++){

            //go char by char in line and add to corresponding party index
            for(int j = 0; j < parties.length; j++){

                char current = br.read();

                //skip if comma
                if(current == ','){
                    continue;
                }

                //increment when 1 is found
                else if(current == '1'){
                    partyBallots[j] += 1;
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
