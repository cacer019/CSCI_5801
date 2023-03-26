/**
 * IRProcessing.java defines the IRProcessing class, which is used to process an IR election
 * and determine its results while documenting the course of the election processing.
 *
 * Written by tracy255, ber00063.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * IRProcessing class used for processing an IR election.
 * Implements IElectionProcessing, an interface outlining election processing.
 */
public class IRProcessing implements IElectionProcessing {

    private static ArrayList<Candidate> candidates;
    /**
     * an integer that keeps track of the total number of ballots that were cast in an election.
     */
    int totalNumBallots = 0;
    /**
     * instantiates a ProcessResults instance, which is used to write election proceedings to an audit file.
     */
    ProcessResults auditFileOutput;


    /**
     * Calls the setCandidates() and distributeBallots() to set up the processing of an IR
     * election and then calls processElection() to process the election.
     * Writes to audit file information about election proceedings.
     * @param br  a BufferedReader, buffers the input from a FileReader that is reading
     *            from the election information csv file
     * @throws IOException  throws an IOException if distributeBallots() throws an IOException
     *                      when called
     */
    public IRProcessing(BufferedReader br) throws IOException {
        candidates = new ArrayList<>();

        auditFileOutput = new ProcessResults();
        auditFileOutput.addVotingType("Instant-Runoff");

        setCandidates(br);
        try {
            distributeBallots(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Candidate curCand : candidates) {
            auditFileOutput.addCandidate(curCand.getCandidateName(), curCand.getParty(), curCand.getBallotCount());
        }
        processElection();
    }


    /**
     * Does the work of actually running an IR election to determine the winner and losers.
     * Writes proceedings to audit file.
     * @return  the name of the election winner as a String, "FAILURE" if error.
     */
    @Override
    public String processElection() {
        while(candidates.size() > 0) {
            System.out.println("totalnumballots: " + totalNumBallots);
            for (Candidate curCand : candidates) {
                //check if there is a winner with a majority number of ballots
                System.out.println("curcandcunt -> " + curCand.getCandidateName() + ": " + curCand.getBallotCount());
                if (curCand.getBallotCount() > (totalNumBallots * 0.5)) {
                    //winner is candidate
                    try {
                        auditFileOutput.addWinner(curCand.getCandidateName(), curCand.getBallotCount());
                    } catch (IOException e) { throw new RuntimeException(e); }
                    System.out.println("----------WINNER: " + curCand.getCandidateName() + "----------");
                    return curCand.getCandidateName();
                }
                //do not need to account for the possibility of a two-way tie, handled in determineLoser
            }
            //Call determineLoser, if only two left check for a tie and break it (look at activity diagram for order)
            Candidate loser = determineLoser();
            try {
                auditFileOutput.addLoser(loser.getCandidateName(), loser.getBallotCount());
            } catch (IOException e) { throw new RuntimeException(e); }
            //redistributeBallots accordingly, removes losing candidate
            redistributeBallots(loser);
        }
        return "FAILURE";
    }

    /**
     * Gets a list of all the political parties that candidates belong to.
     * @return  a String[] containing the political parties of candidates as Strings
     */
    @Override
    public String[] getParties() {
        String[] partyStrings = new String[candidates.size()];
        for(int i = 0; i < candidates.size(); i++){
            partyStrings[i] = candidates.get(i).getParty();
        }
        return partyStrings;
    }

    /**
     * Gets a list of the candidates in the election.
     * @return  a String[] containing the names of the candidates as Strings
     */
    @Override
    public String[] getCandidates() {
        //loops through candidates and adds their names to a String array
        String[] candStrings = new String[candidates.size()];
        for(int i = 0; i < candidates.size(); i++){
            candStrings[i] = candidates.get(i).getCandidateName();
        }
        return candStrings;
    }

    /**
     * Gets the list of candidates in the election.
     * @return  an ArrayList of Candidate objects that represent the candidates in the election
     */
    public ArrayList<Candidate> getCandidateArray() {
        return candidates;
    }

    /**
     * Reads from the election information csv file to create the Candidates.
     * @param br  BufferedReader, buffers the input from a FileReader that is reading
     *            from the election information csv file
     */
    private void setCandidates(BufferedReader br) {
        //Read the second line (candidates
        String curLine;
        try {
            //read the number of candidates, then the names
            String numCands = br.readLine();
            auditFileOutput.addCandidateAmount(numCands);
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

    /**
     * Reads from the election information csv file to distribute Ballots to the Candidates.
     * @param br  BufferedReader, buffers the input from a FileReader that is reading
     *            from the election information csv file.
     * @throws IOException  if IO error occurs when reading file.
     */
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


    /**
     * Determines which candidate to eliminate from the election.
     * Chooses the candidate with the least first choice votes.
     * If there is a tie for candidates with the least first choice votes, a fair coin toss is
     * simulated by randomly choosing a loser.
     * @return  the Candidate that is the loser
     */
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
            System.out.println("TIE" + loserCandidates.get(0).getCandidateName() + " " + loserCandidates.get(1).getCandidateName());
            int randIndex = (int)(Math.random() * loserCandidates.size());
            return loserCandidates.get(randIndex);
        }

    }


    /**
     * Takes a candidate and redistributes their ballots to other candidates.
     * Gives each ballot to the next ranked candidate on that ballot.
     * Accounts for lost ballots when there are no more rankings.
     * @param cand  the candidate whose votes will be redistributed
     */
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
                        try {
                            auditFileOutput.removedBallot(curBallot.getIndex());
                        } catch (IOException e) { throw new RuntimeException(e); }
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
