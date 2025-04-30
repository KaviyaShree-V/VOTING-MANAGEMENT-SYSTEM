import java.util.HashMap;
import java.util.Map;

public class ElectionService {

    public void createElection(String electionName) {
        Election election = new Election(electionName);
        Election.getElections().put(electionName, election);
        System.out.println("Election " + electionName + " created.");
    }

    public void addCandidateToElection(String electionName, String candidateName) {
        Election election = Election.getElections().get(electionName);
        if (election != null && election.isActive()) {
            election.addCandidate(new Candidate(candidateName));
            System.out.println("Candidate " + candidateName + " added to election " + electionName);
        } else {
            System.out.println("Election not found or closed.");
        }
    }

    public Election getElection(String electionName) {
        return Election.getElections().get(electionName);
    }

    public void closeElection(String electionName) {
        Election election = Election.getElections().get(electionName);
        if (election != null) {
            election.closeElection();
            System.out.println("Election " + electionName + " is now closed.");
        }
    }
}
