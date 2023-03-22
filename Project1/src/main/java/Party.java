public class Party implements IRepresentative {

    private String[] candidates;
    private int numSeats;
    private String partyName;

    public Party(String party, String[] candidateList) {
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
        candidates = newCandidates;
    }

    public void removeCandidate(String candidateToRemove) {
        int n = candidates.length;
        String newCandidates[] = new String[n - 1];
        for (int i = 0, k = 0; i < n; i++) {
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

    @Override
    public int getBallotCount() {
        return 0;
    }

    @Override
    public String getParty() {
        return null;
    }
}
