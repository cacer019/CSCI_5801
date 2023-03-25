
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class IRProcessing implements IElectionProcessing {

    private static ArrayList<Candidate> candidates;
    int totalNumBallots = 0;

    public IRProcessing(BufferedReader br) {
        candidates = new ArrayList<>();
        setCandidates(br);
        try {
            distributeBallots(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        processElection();
    }

    @Override
    public String processElection() {
        while(candidates.size() > 0) {
            System.out.println("totalnumballots: " + totalNumBallots);
            for (Candidate curCand : candidates) {
                //check if there is a winner with a majority number of ballots
                System.out.println("curcandcunt -> " + curCand.getCandidateName() + ": " + curCand.getBallotCount());
                if (curCand.getBallotCount() > (totalNumBallots * 0.5)) {
                    //winner is candidate
                    System.out.println("----------WINNER: " + curCand.getCandidateName() + "----------");
                    return curCand.getCandidateName();
                }
                //do not need to account for the possibility of a two-way tie, handled in determineLoser
            }
            //Call determineLoser, if only two left check for a tie and break it (look at activity diagram for order)
            Candidate loser = determineLoser();
            //redistributeBallots accordingly, removes losing candidate
            redistributeBallots(loser);
        }
        return "FAILURE";
    }

    @Override
    public String[] getParties() {
        String[] partyStrings = new String[candidates.size()];
        for(int i = 0; i < candidates.size(); i++){
            partyStrings[i] = candidates.get(i).getParty();
        }
        return partyStrings;
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

    public ArrayList<Candidate> getCandidateArray() {
        return candidates;
    }


    private void setCandidates(BufferedReader br) {
        //Read the second line (candidates
        String curLine;
        try {
            //read the number of candidates, then the names
            br.readLine();
            curLine = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //need to remove all whitespaces
        curLine = curLine.replaceAll("\\s+","");
        //System.out.println("curline: " + curLine);  //debugging
        String[] candNames = curLine.split(",");
        for (String curCandName : candNames) {
            //Split name from party
            //System.out.println("curCandName: " + curCandName);  //debugging
            String[] candData = curCandName.split("\\(");
            //System.out.println("cand0: " + candData[0]);  //debugging
            //System.out.println("cand1: " + candData[1]);  //debugging
            //Remove parenthesis around party
            String fixedPartyName = candData[1].replace(")", "");
            //Make a new candidate
            //System.out.println("party: " +fixedPartyName);  //debugging
            //System.out.println("name: " + candData[0]);  //debugging
            Candidate newCand = new Candidate(fixedPartyName, candData[0]);
            //Add new Candidate object into class variable candidates
            candidates.add(newCand);
        }
    }

    private void distributeBallots(BufferedReader br) throws IOException {
        //Get the 4th line of the CSV file
        String nextLine = br.readLine();
        int ballotCount = Integer.parseInt(nextLine);
        int ballotIndex = 0;
        //System.out.println("Read: " + ballotCount);  //debugging

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
                if(ballotRankings[y] != "") {
                    int curRanking = Integer.parseInt(ballotRankings[y]);
                    String candidateName = candidates.get(curRanking - 1).getCandidateName();
                    //System.out.println("added: " + candidateName);  //debugging
                    tempRankings.add(candidateName);
                    //Increment the num rankings
                    curNumRankings++;
                }
            }
            //Create the new ballot
            //System.out.println("ballotIndex: " + ballotIndex);  //debugging
            //System.out.println("curNumRankings: " + curNumRankings);  //debugging
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
            System.out.println("TIE" + loserCandidates.get(0).getCandidateName() + loserCandidates.get(1).getCandidateName());
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
            System.out.println("ballot " + curBallot.getIndex() + " : " + curBallot.getNumRankings());
            boolean updateBallotResult = curBallot.updateBallot(); //update ballot
            if (!updateBallotResult) {  //if false is returned, delete this ballot (no more rankings)
                //TODO: use getIndex() for audit file
                //remove this ballot by ignoring it, once this candidate is deleted
                //the ballots are destroyed by the garbage collector
                System.out.println("REMOVED A BALLOT");
                totalNumBallots--;
            }
            else {
                //TODO: check new candidate exists or update again
                Boolean checkIfCandExists = false;
                outer: while(checkIfCandExists == false) {
                    for (Candidate curCand : candidates) {
                        if (curBallot.getNextCandidate() == curCand.getCandidateName()) {
                            checkIfCandExists = true;
                            break outer;
                        }
                    }
                    updateBallotResult = curBallot.updateBallot();
                    if (!updateBallotResult) {  //if false is returned, delete this ballot (no more rankings)
                        //TODO: use getIndex() for audit file
                        //remove this ballot by ignoring it, once this candidate is deleted
                        //the ballots are destroyed by the garbage collector
                        System.out.println("REMOVED A BALLOT - 2");
                        totalNumBallots--;
                        break outer;
                    }
                }

                if(checkIfCandExists == true) {
                    nextCand = curBallot.getNextCandidate();  //get candidate to redistribute to
                    for (Candidate curCand : candidates) {     //find candidate to redistribute to
                        if (curCand.getCandidateName().equals(nextCand)) {
                            //redistribute to candidate; addBallot() updates Candidate BallotCount
                            curCand.addBallot(curBallot);
                        }
                    }
                }
            }
        }
        //FIXME: possible issue with using loose equality within remove, removing first instance of this candidate
        //todo: may need to get this candidate's name and manually remove from candidates with for loop
        System.out.println("removed candidate: " + cand.getCandidateName());
        candidates.remove(cand);
    }

}
