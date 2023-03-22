import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class IRProcessing implements IElectionProcessing {

    private static ArrayList<Candidate> candidates;

    @Override
    public boolean processElection(BufferedReader br) {
        setCandidates(br);
        distributeBallots(br);
        //Call determineLoser, if only two left check for a tie and break it (look at activity diagram for order)
        //redistributeBallots accordingly, need to call updateBallot on all that candidates ballots,
        //then add them to the next candidate's Ballots, and finally remove losing candidate from this.candidates

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
        String curLine = br.readLine();
        String[] candidates = curLine.split(",");
        for (String candidate : candidates) {
            //Split name from party
            String[] candData = candidate.split(" ");
            //Remove parenthesis around party
            candData[1].replace("(", "");
            candData[1].replace(")", "");
            //Make a new candidate
            Candidate newCand = new Candidate(candData[0], candData[1]);
            //Add new Candidate object into class variable candidates
            this.candidates.add(newCand);
        }
    }

    private void distributeBallots(BufferedReader br) throws IOException {
        //Get the 4th line of the CSV file
        int ballotCount = Integer.parseInt(br.readLine());
        int ballotIndex = 0;

        //Loop through all the lines to create each individual ballot
        for(int i = 0; i < ballotCount; i++) {
            //Temporary variables for ballot creation
            ArrayList<String> tempRankings = new ArrayList<>();
            int curNumRankings = 0;
            String[] ballotRankings = (br.readLine()).split(",");
            //Go through each ranking and add accordingly
            for(int y = 0; y < ballotRankings.length; y++) {
                //Get candidates name for current position, if ranked add the candidates name
                //into that position in the rankings accordingly
                String candidateName = this.candidates.get(y).getCandidateName();
                tempRankings.add(y, candidateName);
                //Increment the num rankings
                curNumRankings++;
            }
            //Create the new ballot
            Ballot tempBallot = new Ballot(ballotIndex, curNumRankings, tempRankings);
            //Need to add this Ballot to it's first choice's Ballots
            String candidateToFind = tempBallot.getNextCandidate();
            for (Candidate curCand : this.candidates) {
                if(curCand.getCandidateName().equals(candidateToFind)) {
                    curCand.addBallot(tempBallot);
                }
            }
            //curCand.get(i).addBallot(tempBallot);
            //Increment to the next ballot number
            ballotIndex++;
        }
    }



    public Candidate determineLoser() {
        int minVotes = Integer.MAX_VALUE;
        int tempVotes;
        ArrayList<Candidate> loserCandidates = new ArrayList<Candidate>();

        for (Candidate candidate : candidates) {
            tempVotes = candidate.getBallotCount();
            if(tempVotes < minVotes){       //if candidate has less votes than current min, clear list and add them
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
        else{
            return loserCandidates.get((int)(Math.random()*loserCandidates.size()));
            /*
            (int)(Math.random()*loserCandidates.size()) gets random int from 0 to
            the number of loserCandidates-1 to randomly choose loser
            */
        }

    }


    public void redistributeBallots(Candidate cand){
        String nextCand;

        for(Ballot curBall: cand.getBallots()){
            curBall.updateBallot(); //update ballot
            nextCand = curBall.getNextCandidate();  //get candidate to redistribute to
            for(Candidate curCand: candidates){     //find candidate to redistribute to
                if(curCand.getCandidateName().equals(nextCand)){
                    curCand.addBallot(curBall);     //redistribute to candidate
                }
            }
        }
    }


}
