import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class IRProcessing implements IElectionProcessing {

    private static ArrayList<Candidate> candidates;
    int totalNumBallots = 0;

    @Override
    public boolean processElection(BufferedReader br) {
        candidates = new ArrayList<>();
        setCandidates(br);
        try {
            distributeBallots(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(candidates.size() > 0) {
            for (Candidate curCand : candidates) {
                //check if there is a winner with a majority number of ballots
                if (curCand.getBallotCount() > (totalNumBallots * 0.5)) {
                    //winner is candidate
                    return true;
                }
                //do not need to account for the possibility of a two-way tie, handled in determineLoser
            }
            //Call determineLoser, if only two left check for a tie and break it (look at activity diagram for order)
            Candidate loser = determineLoser();
            //redistributeBallots accordingly, removes losing candidate
            redistributeBallots(loser);
        }
        return false;
    }

    @Override
    public String[] getCandidates() {
        //loops through candidates and adds their names to a String array
        String[] candStrings = new String[candidates.size()];
        for(int i = 0; i < candidates.size(); i++){
            candStrings[i] = candidates.get(i).getCandidateName();
        }
        return candStrings;
    }

    @Override
    public String[] getParties() {
        return new String[0];
    }

    private void setCandidates(BufferedReader br) {
        //Read the second line (candidates
        String curLine;
        try {
            curLine = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] candNames = curLine.split(",");
        for (String curCandName : candNames) {
            //Split name from party
            String[] candData = curCandName.split(" ");
            //Remove parenthesis around party
            String fixedPartyName = candData[1].replace("(", "");
            fixedPartyName = fixedPartyName.replace(")", "");
            //Make a new candidate
            Candidate newCand = new Candidate(candData[0], fixedPartyName);
            //Add new Candidate object into class variable candidates
            candidates.add(newCand);
        }
    }

    private void distributeBallots(BufferedReader br) throws IOException {
        //Get the 4th line of the CSV file
        int ballotCount = Integer.parseInt(br.readLine());
        int ballotIndex = 0;

        //Loop through all the lines to create each individual ballot, O(2) time
        for(int i = 0; i < ballotCount; i++) {
            //Temporary variables for ballot creation
            ArrayList<String> tempRankings = new ArrayList<>();
            int curNumRankings = 0;
            String[] ballotRankings = (br.readLine()).split(",");
            //Go through each ranking and add accordingly
            for(int y = 0; y < ballotRankings.length; y++) {
                //Get candidates name for current position, if ranked add the candidates name
                //into that position in the rankings accordingly
                String candidateName = candidates.get(y).getCandidateName();
                tempRankings.add(y, candidateName);
                //Increment the num rankings
                curNumRankings++;
            }
            //Create the new ballot
            Ballot tempBallot = new Ballot(ballotIndex, curNumRankings, tempRankings);
            //Need to add this Ballot to it's first choice's Ballots
            String candidateToFind = tempBallot.getNextCandidate();
            for (Candidate curCand : candidates) {
                if(curCand.getCandidateName().equals(candidateToFind)) {
                    curCand.addBallot(tempBallot);
                }
            }
            //curCand.get(i).addBallot(tempBallot);
            //Increment to the next ballot number
            ballotIndex++;
            totalNumBallots++;
        }
    }



    public Candidate determineLoser() {
        int minVotes = Integer.MAX_VALUE;
        int tempVotes;
        ArrayList<Candidate> loserCandidates = new ArrayList<>();

        for (Candidate candidate : candidates) {
            tempVotes = candidate.getBallotCount();
            if(tempVotes < minVotes){       //if candidate has fewer votes than current min, clear list and add them
                minVotes = tempVotes;
                loserCandidates.clear();
                loserCandidates.add(candidate);
            }
            else if(tempVotes == minVotes){     //if candidate has same votes as current min, add them
                loserCandidates.add(candidate);
            }
        }

        //if only one min candidate, return them, else randomly choose loser
        if(loserCandidates.size() == 1){
            return loserCandidates.get(0);
        }
        else {
            //the idea here is that there can be multiple losers tied, but only one is randomly chosen as the loser
            //gets random int from 0 to number of possible losers
            int randIndex = (int)(Math.random() * loserCandidates.size());
            return loserCandidates.get(randIndex);
        }

    }

    //Takes a candidate, and passes their ballots onto their next ranked candidate
    //accounts for lost ballots when there are no more rankings
    public void redistributeBallots(Candidate cand){
        //nextCand is used in for each loop
        String nextCand;
        for(Ballot curBallot: cand.getBallots()) {
            boolean updateBallotResult = curBallot.updateBallot(); //update ballot
            if (!updateBallotResult) {  //if false is returned, delete this ballot (no more rankings)
                //TODO: use getIndex() for audit file
                //remove this ballot by ignoring it, once this candidate is deleted
                //the ballots are destroyed by the garbage collector
                totalNumBallots--;
            }
            else {
                nextCand = curBallot.getNextCandidate();  //get candidate to redistribute to
                for (Candidate curCand : candidates) {     //find candidate to redistribute to
                    if (curCand.getCandidateName().equals(nextCand)) {
                        //redistribute to candidate; addBallot() updates Candidate BallotCount
                        curCand.addBallot(curBallot);
                    }
                }
            }
        }
        //FIXME: possible issue with using loose equality within remove, removing first instance of this candidate
        //todo: may need to get this candidate's name and manually remove from candidates with for loop
        candidates.remove(cand);
    }

}
