/**
 * main.java is the main file for CSCI 5801 Project 3: Waterfall Implementation & Testing.
 * This is where the user is prompted for the election file, the type of election is determined,
 * and from there the election is processed based on its type.
 *
 * Written by tracy255.
*/

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * main class where user will be prompted and election system will be run.
 */
public class main {
    /**
     * The main method. Calls getInput() and checkArgs() to check user input and run election.
     * Checks if user gives too many arguments or for checkArgs() error.
     * @param args  the command line arguments given by the user.
     * @throws IOException  when getInput() or checkArgs() throws an exception.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("------MAIN INITIALIZED------");
        Boolean checkReturn;
        if(args.length == 0) {
            String fileName = getInput();
            checkReturn = checkArgs(args[1]);
        }
        else if(args.length > 1) {
            System.out.println("------Please only input the filename------");
            checkReturn = false;
        }
        else {
            checkReturn = checkArgs(args[1]);
            if (!checkReturn) {
                System.out.println("------CHECKARGS FAILURE------");
            }
        }

        if (!checkReturn) {
            for(int i = 0; i < 10; i++) {
                System.out.println("------Input filename from working directory. Format: 'FileName'.csv------");
                String fileName = getInput();
                checkReturn = checkArgs(args[1]);
                if(checkReturn == true) {
                    break;
                }
            }
        }
    }


    /**
     * Checks the command line argument given by the user to get the election information file.
     * Checks if input file can't be found.
     * Calls CPLProcessing.processElection() if election is CPL.
     * Calls IRProcessing.processElection() if election is IR.
     * @param fileName  name of election information file given by user.
     * @return  a boolean, true if election was run, false if not.
     * @throws FileNotFoundException throws exception if file couldn't be found.
     */
    private static boolean checkArgs(String fileName) throws FileNotFoundException {
        FileReader csvFile = new FileReader(fileName);
        //If file cannot be found, throw an exception.
        String electionType;
        try {
            BufferedReader myReader = new BufferedReader(csvFile);
            electionType = myReader.readLine();
        } catch (Exception e) {
            System.out.println("File cannot be found.\nFile-name provided: " + fileName);
            throw new RuntimeException(e);
        }
        if (electionType.equals("CPL")) {
            //CPLProcessing.processElection(myReader);
            return true;
        }
        else if (electionType.equals("IR")) {
            //IRProcessing.processElection(myReader);
            return true;
        }
        return false;
    }

    /**
     * Reads user input from command line to get fileame.
     * @return  name of file input by user as a String.
     * @throws IOException  when IO error occurs.
     */
    private static String getInput() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String file = inputReader.readLine();
        return file;
    }

//    private static String getElectionType(Scanner sc) {
//
//    }

}
