
import java.util.ArrayList;

public class IRRow {
    private String CandName;
    private String CandParty;
    private ArrayList<Integer> ballot_stats;

    public IRRow(String candName, String candParty) {
        this.CandName = candName;
        this.CandParty = candParty;
        this.ballot_stats = new ArrayList<>();
    }
    public String getCandName() {
        return CandName;
    }

    public void setCandName(String candName) {
        CandName = candName;
    }

    public String getCandParty() {
        return CandParty;
    }

    public void setCandParty(String candParty) {
        CandParty = candParty;
    }

    public ArrayList<Integer> get_stats() {
        return ballot_stats;
    }

    public void add_stat(int ballot_count) {
        this.ballot_stats.add(ballot_count);
    }

    public int get_length() {
        return this.ballot_stats.size();
    }
}
