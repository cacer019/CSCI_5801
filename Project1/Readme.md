# Team25: tracy255, ber00063, cacer019, abouz009

### Compilation
Users are assumed to be using IntelliJ, and run main.main() using their IDE.
Working directory is assumed to be Project1.
Modules include Project1, main (subdirectory of src), and test subdirectory of src.
SDK version is 17.
This project uses Gradle library manager.

### Project Structure
Project structure differs from expected team directory structure. Rather than a src
and testing subdirectory underneath the Project1 directory, src contains main
(equivalent of src in expected directed) and test (equivalent of testing in expected
directory). Each contains a "java" subdirectory where java files and csv files for
testing are stored. Otherwise, the file structure meets expectations.

### Special case: CSV file location
Any csv file used for running the program should be located in the working directory
which is assumed to be Project1. The checkArgs() method in main will not locate the
csv file if it is not under "Project1/".

### Special case: Audit file location
The csv file will be placed under the current working directory, which should be 
Project1.
