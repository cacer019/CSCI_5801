/*
    CSCI 5801 Project 3: Waterfall Implementation & Testing
    tracy255,
*/

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        System.out.println("------MAIN INITIALIZED------");
        Boolean checkReturn;
        if(args.length == 0) {
            System.out.println("------Getting input------");
            String fileName = getInput();
            checkReturn = checkArgs(fileName);
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
                checkReturn = checkArgs(fileName);
                if(checkReturn == true) {
                    break;
                }
            }
        }
    }

    private static boolean checkArgs(String fileName) throws IOException {
        String path = "Project1/" + fileName;
        File f = new File(path);
        if(f.exists()) {
            System.out.println("file exists!");
        }
        else {
            System.out.println("file does not exist!");
            return false;
        }
        FileReader csvFile = new FileReader(path);
        //If file cannot be found, throw an exception.
        String electionType;
        BufferedReader myReader = new BufferedReader(csvFile);
        try {
            electionType = myReader.readLine();
        } catch (Exception e) {
            System.out.println("File cannot be found.\nFile-name provided: " + fileName);
            throw new RuntimeException(e);
        }
        if (electionType.equals("CPL")) {
            //runs processElection() in the constructor
            CPLProcessing CPLElection = new CPLProcessing(myReader);
            return true;
        }
        else if (electionType.equals("IR")) {
            //runs processElection() in the constructor
            IRProcessing IRElection = new IRProcessing(myReader);
            return true;
        }
        return false;
    }

    //Read input from terminal
    private static String getInput() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String file = inputReader.readLine();
        //System.out.println("filename: " + file);
        return file;
    }


}
