/**
 * IElectionProcessing.java defines the IElectionProcessing interface that is implemented by
 * the IRProcessing and CPLProcessing classes.
 * It provides the outline for an election processing class.
 *
 * Written by tracy255.
 */

import java.io.BufferedReader;

/**
 * IElectionProcessing interface provides an outline for an election processing class.
 */
public interface IElectionProcessing {

    /**
     * Runs an election until a winner is determined, documenting how the election proceeds.
     * @return  the names of the winner(s) of the election as a String
     */
    public String processElection();

    /**
     * Gets the names of candidates in the election
     * @return  a String[] containing the names of candidates in the election
     */
    public String[] getCandidates();

}
