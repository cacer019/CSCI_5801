
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class ProcessResults {

    private File auditFile;

    public ProcessResults() throws IOException {
        //get today's today to add onto the file title
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = formatter.format(date);

        String fileName = "auditFile" + strDate + ".txt";
        File newFile = new File(fileName);
        auditFile = newFile;
        if(!auditFile.exists()) {
            auditFile.createNewFile();
        }
//        if(!newFile.createNewFile()) {
//            //erase the current contents if this file already exists
//            PrintWriter writer = new PrintWriter(newFile);
//            writer.append("");
//            writer.flush();
//        }
    }

    public void addVotingType(String type) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Election Type: " + type + "\n");
        bw.close();
    }

    void addCandidateAmount(String num) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Number of candidates: " + num + "\n");
        bw.close();
    }

    void addCandidate(String name, String party, int initialBallots) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("New Candidate:\n+++Candidate name: " + name + "\n+++Party name: " + party + "\n");
        bw.write("+++Initial ballot count: " + Integer.toString(initialBallots) + "\n");
        bw.close();
    }

    void addLoser(String name, int finalBallotCount) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Removed loser:\n+++Candidate name: " + name + "\n");
        bw.write("+++Final ballot count: " + Integer.toString(finalBallotCount) + "\n");
        bw.close();
    }

    void removedBallot( int index) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        //bw.write("Ballot removed:\n+++Final candidate name: " + name + "\n");
        //bw.write("+++Ballot index: " + Integer.toString(index) + "\n");
        bw.write("Ballot removed:\n+++Ballot index: " + Integer.toString(index) + "\n");
        bw.close();
    }

    void addWinner(String name, int finalBallotCount) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("ELECTION WINNER:\n+++Candidate name: " + name + "\n");
        bw.write("+++Final ballot count: " + Integer.toString(finalBallotCount) + "\n");
        bw.write("\n\n\n");
        bw.close();
    }

}
