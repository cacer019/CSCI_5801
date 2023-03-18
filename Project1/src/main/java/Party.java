public class Party implements IRepresentative {

    private String[] candidates;
    private int numSeats;

    public void Party(String party, String[] candidateList) {
        partyName = party;
        candidates = candidateList;
    };

    public void addCandidate(String candidateName) {
        int n = candidates.length;
        String[] newCandidates = new String[n+1];
        for (int i = 0; i < n; i++) {
            newCandidates[i] = candidates[i];
        }
        newCandidates[n] = candidateName;
        candidates = newCandidates
    }

    public void removeCandidate(String candidateToRemove) {
        int n = candidates.lengh;
        String newCandidates[] = new String[n - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (candidates[i] != candidateToRemove) {
                newCandidates[k] = candidates[i];
                k++;
            }
        }
        candidates = newCandidates;
    }

    public String[] getCandidate() {
        return candidates;
    }
}
