# Team25: tracy255, ber00063, cacer019, abouz009

### Compilation
Users are assumed to be using IntelliJ, and run main.main() using their IDE.
Working directory is assumed to be Project1.
Modules include Project1, main (subdirectory of src), and test (subdirectory of src).
All java files should be in the same default package using IntelliJ, as no files 
have package declarations.
SDK version is 17.
This project uses Gradle library manager.

### Project Structure (test and source files in new location)
Project structure differs from expected team directory structure. Rather than a src
and testing subdirectory underneath the Project1 directory, src contains main
(equivalent of src in expected directed) and test (equivalent of testing in expected
directory). Each contains a "java" subdirectory where java files and csv files for
testing are stored. Otherwise, the file structure meets expectations.

### Special case: Testing
As instructed csv test files for system testing were moved in the "testing" directory
underneath "Project1". They are necessary for running the tests within CPLProcessingTest 
and IRProcessingText files and must be moved back into those folders if those tests are ran.

### Special case: Input CSV file location
Any csv file used for running the program should be located under the working directory
which is assumed to be Project1. The checkArgs() method in main will not locate the
csv file if it is not under "Project1/".

### Special case: Audit file location
The csv file will be placed alongside the current working directory, which should be 
Project1. If using IntelliJ you can see it alongside .gitignore and README.md.
