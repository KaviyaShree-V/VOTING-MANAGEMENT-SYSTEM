import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Election {
    private String electionName;
    private List<Candidate> candidates;
    private boolean isActive;
    private static Map<String, Election> elections = new HashMap<>();

    public Election(String electionName) {
        this.electionName = electionName;
        this.candidates = new ArrayList<>();
        this.isActive = true;
    }

    public String getElectionName() {
        return electionName;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public void closeElection() {
        this.isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public static Map<String,Election> getElections() {
        return elections;
    }

    public void setElections(Map<String,Election> elections) {
        this.elections = elections;
    }
}
