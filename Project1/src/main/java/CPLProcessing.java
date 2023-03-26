import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CPLProcessing implements IElectionProcessing {

    private ArrayList<Party> parties;
    private int numParties;
    private int numAvailableSeats;
    private int numBallots;

    public CPLProcessing(BufferedReader br) throws IOException {
        this.parties = new ArrayList<>();
        setParties(br);
        try {
            distributeBallots(br, this.parties);
            distributeSeats();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        processElection();
    }

    private void distributeSeats() {
        // Calculates quota by dividing number of seats by the number of ballots.

        int quota = getNumBallots() / getNumAvailableSeats();

        ArrayList<Integer> remainders = new ArrayList<>();
        // Allocates seats to parties based on quota and calculates the remainders
        for (Party party : this.parties) {
            party.setNumSeats(party.getBallotCount() / quota);
            setNumAvailableSeats(getNumAvailableSeats() - party.getBallotCount() / quota);
            remainders.add(party.getBallotCount() % quota);
        }
        // Allocates the rest of the seats based on highest remainder
        while (getNumAvailableSeats() > 0) {
            int highestRemainder = Collections.max(remainders);
            int seatIndex = remainders.indexOf((highestRemainder));
            // Checks to see if there is a tie for remainder
            ArrayList<Integer> tiedIndexes = new ArrayList<>();
            tiedIndexes.add(seatIndex);
            for (int i = 0; i < remainders.size(); i++) {
                if (seatIndex != i) {
                    if (remainders.get(i) == highestRemainder) {
                        tiedIndexes.add(i);
                    }
                }
            }
            if (tiedIndexes.size() > 1) {
                // Chooses from select group of parties that tied for highest remainder of ballots
                int randomNum = (int)(Math.random() * tiedIndexes.size());
                int chosenIndex = tiedIndexes.get(randomNum);
                this.parties.get(chosenIndex).setNumSeats(this.parties.get(chosenIndex).getNumSeats() + 1);
            } else {
                // Adds seat with the highest remainder
                this.parties.get(seatIndex).setNumSeats(this.parties.get(seatIndex).getNumSeats() + 1);
            }
            setNumAvailableSeats(getNumAvailableSeats() - 1);
        }
    }

    private void setParties(BufferedReader br) throws IOException {
        // Gets number if parties (2nd Line)
        int numParties = Integer.parseInt(br.readLine());
        setNumParties(numParties);
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

    private void distributeBallots(BufferedReader br, ArrayList<Party> parties) throws IOException {

        // Reads number of available seats
        int numSeats = Integer.parseInt(br.readLine());
        setNumAvailableSeats(numSeats);

        // Reads number of ballots that were submitted
        int numBallots = Integer.parseInt(br.readLine());
        setNumBallots(numBallots);

        // Iterate through each line and look for ballot vote position
        for(int i = 0; i < getNumBallots(); i++){

            // Go char by char for each ballot make string 1st
            String currentBallot = br.readLine();
            // Convert Ballot string into char array
            char[] ballotArray = currentBallot.toCharArray();

            // Iterate through the char array and distribute ballot
            for(int j = 0; j < ballotArray.length; j++){

                if(ballotArray[j] == ','){
                    continue;
                }

                else if(ballotArray[j] == '1'){
                    // Unsure here if we can do this, maybe we create setter in Party class to set ballot count
                    parties.get(j).setBallotCount(parties.get(j).getBallotCount() + 1);
                }
            }
        }
    }
    @Override
    public String processElection() {
        System.out.println("---------- Seats that are allocated ----------");
        for (Party party : parties) {
            for (int i = 0; i < party.getNumSeats(); i++) {
                System.out.println(party.getCandidates().get(i) + " (" + party.getParty() + ")");
            }
        }
        return "SUCCESS";
    }

    public int getNumParties() {
        return this.numParties;
    }

    public void setNumParties(int numParties) {
        this.numParties = numParties;
    }

    public int getNumAvailableSeats() {
        return this.numAvailableSeats;
    }

    public void setNumAvailableSeats(int numSeats) {
        this.numAvailableSeats = numSeats;
    }

    public int getNumBallots() {
        return this.numBallots;
    }

    public void setNumBallots(int numBallots) {
        this.numBallots = numBallots;
    }

    @Override
    // Not used
    public String[] getCandidates() {
        return new String[0];
    }

    @Override
    // Not used
    public String[] getParties() {
        return new String[0];
    }
}
