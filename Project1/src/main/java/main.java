
/**
 * main.java is the main file for CSCI 5801 Project 3: Waterfall Implementation & Testing.
 * This is where the user is prompted for the election file, the type of election is determined,
 * and from there the election is processed based on its type.
 *
 * @author tracy255, Caleb Tracy.
 */


import java.io.*;

/** main class where user will be prompted for election information file
 *  and election system will be run.
 */
public class main {

    /**
     * The main method. Calls getInput() and checkArgs() to check user input and run election.
     * Checks if user gives too many arguments or for checkArgs() error.
     * @param args  the command line arguments given by the user.
     * @throws IOException  when getInput() or checkArgs() throws an exception.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("------ELECTION PROCESSING PROGRAM RUNNING------");
        Boolean checkReturn;  //Used to determine if the current input it valid
        if(args.length == 0) {
            System.out.println("------Input filename from working directory. Format: 'FileName'.csv------");
            String fileName = getInput();
            checkReturn = checkArgs(fileName);
        }
        else if(args.length > 1) {  //More inputs than the filename were given
            System.out.println("------Please only input the filename------");
            checkReturn = false;
        }
        else {
            //In the case only one input is provided
            checkReturn = checkArgs(args[1]);
            if (!checkReturn) {  //Check if the name is valid
                System.out.println("------PROVIDED FILENAME IS INCORRECT------");
            }
        }

        if (!checkReturn) {
            for(int i = 0; i < 10; i++) {
                System.out.println("------INVALID FILENAME: Input filename from working directory. Format: 'FileName'.csv------");
                String fileName = getInput();
                checkReturn = checkArgs(fileName);
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
    private static boolean checkArgs(String fileName) throws IOException {
        String path = "Project1/" + fileName;
        File f = new File(path);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        //Check if the file exists
        if(f.exists()) {
            System.out.println("file exists!");
        }
        else {
            System.out.println("file does not exist!");
            return false;
        }
        FileReader csvFile = new FileReader(path);
        //If file cannot be found, throw an exception.
        //Extract the election type from the CSV file (first line)
        String electionType;
        BufferedReader myReader = new BufferedReader(csvFile);
        try {
            electionType = myReader.readLine();
        } catch (Exception e) {
            System.out.println("File cannot be found.\nFile-name provided: " + fileName);
            throw new RuntimeException(e);
        }

        //Create a processing class object accordingly and pass on the BufferedReader object
        if (electionType.equals("CPL")) {
            //runs processElection() in the constructor
            System.out.println("------Running Closed Party List Election------");
            CPLProcessing CPLElection = new CPLProcessing(myReader);
            return true;
        }
        else if (electionType.equals("IR")) {
            //runs processElection() in the constructor
            System.out.println("------Running Instant Runoff Vote Election------");
            IRProcessing IRElection = new IRProcessing(myReader);
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
        //Read the user input from terminal
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String file = inputReader.readLine();
        //System.out.println("filename: " + file);  //debugging
        return file;
    }


}
