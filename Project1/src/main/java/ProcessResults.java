/**
 * ProcessResults.java is used for writing the proceedings and results of an election to an audit file,
 * which is a .txt file with the name <electiontype><date>.txt
 *
 * Written by tracy255, abouz009
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * ProcessResults is used in election processing to write election results and proceedings
 * to an audit file.
 */
public class ProcessResults {

    private File auditFile;

    /**
     * Creates an audit file for election results and proceedings.
     * @throws IOException  if IO error occurs when creating the audit file.
     */
    public ProcessResults(String electionType) throws IOException {
        //get today's today to add onto the file title
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = formatter.format(date);

        String fileName = "auditFile-" + electionType + "-" + strDate + ".txt";
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


    /**
     * Writes the type of election to the audit file.
     * @param type  the type of the election, IR or CPL, as a String.
     * @throws IOException  if IO error occurs when writing to audit file.
     */
    public void addVotingType(String type) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Election Type: " + type + "\n");
        bw.close();
    }


    /**
     * Writes the number of candidates to the audit file.
     * @param num  the number of candidates in the election as a String.
     * @throws IOException
     */
    void addCandidateAmount(String num) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Number of candidates: " + num + "\n");
        bw.close();
    }

    /**
     * Writes a candidate to the audit file.
     * Adds candidate name, political party, and initial number of ballots they received.
     * @param name  the name of the candidate as a String.
     * @param party  the name of the candidate's political party as a String.
     * @param initialBallots  the number of initial ballots the candidate received as an int.
     * @throws IOException  if IO error occurs when writing to the audit file.
     */
    void addCandidate(String name, String party, int initialBallots) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("New Candidate:\n+++Candidate name: " + name + "\n+++Party name: " + party + "\n");
        bw.write("+++Initial ballot count: " + Integer.toString(initialBallots) + "\n");
        bw.close();
    }

    /**
     * Writes a candidate who was eliminated from the election to the audit file.
     * Adds the candidate's name and their final ballot count before elimination.
     * @param name  the name of the eliminated candidate as a String.
     * @param finalBallotCount  the candidate's final ballot count as an int.
     * @throws IOException  if an IO error occurs when writing to the audit file.
     */
    void addLoser(String name, int finalBallotCount) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Removed loser:\n+++Candidate name: " + name + "\n");
        bw.write("+++Final ballot count: " + Integer.toString(finalBallotCount) + "\n");
        bw.close();
    }

    /**
     * Writes that a ballot was removed if all its candidates were eliminated to the audit file.
     * Writes the unique ballot index to the file for identification.
     * @param index  the ballot index of the removed ballot as an int.
     * @throws IOException  if IO error occurs when writing to the audit file.
     */
    void removedBallot( int index) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        //bw.write("Ballot removed:\n+++Final candidate name: " + name + "\n");
        //bw.write("+++Ballot index: " + Integer.toString(index) + "\n");
        bw.write("Ballot removed:\n+++Ballot index: " + Integer.toString(index) + "\n");
        bw.close();
    }

    /**
     * Writes the winner of the election to the audit file.
     * Writes the winner's name and the final number of ballots they received.
     * @param name  the name of the winner as a String.
     * @param finalBallotCount  the winner's final number of ballots as an int.
     * @throws IOException  if IO error occurs when writing to the audit file.
     */
    void addWinner(String name, int finalBallotCount) throws IOException {
        FileWriter fw = new FileWriter(auditFile.getName(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("ELECTION WINNER:\n+++Candidate name: " + name + "\n");
        bw.write("+++Final ballot count: " + Integer.toString(finalBallotCount) + "\n");
        bw.write("\n\n\n");
        bw.close();
    }

}
