/*
    CSCI 5801 Project 3: Waterfall Implementation & Testing
    tracy255,
*/

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
<<<<<<< Updated upstream

    public static void main(String[] args) {
        
    }

=======
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

    private static String getInput() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String file = inputReader.readLine();
        return file;
    }

//    private static String getElectionType(Scanner sc) {
//
//    }
>>>>>>> Stashed changes
}
