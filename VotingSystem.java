import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VotingSystem {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create Election (Admin)");
            System.out.println("4. Add Candidate (Admin)");
            System.out.println("5. Vote");
            System.out.println("6. View Results");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    createElection(scanner);
                    break;
                case 4:
                    addCandidate(scanner);
                    break;
                case 5:
                    vote(scanner);
                    break;
                case 6:
                    viewResults(scanner);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        VoteSys.getUsers().put(username, new User(username, password));
        System.out.println("User registered successfully.");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (VoteSys.getUsers().containsKey(username) && VoteSys.getUsers().get(username).getPassword().equals(password)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void createElection(Scanner scanner) {
        System.out.print("Enter election name: ");
        String electionName = scanner.nextLine();
        VoteSys.getElectionService().createElection(electionName);
    }

    private static void addCandidate(Scanner scanner) {
        System.out.print("Enter election name: ");
        String electionName = scanner.nextLine();
        Election election = VoteSys.getElectionService().getElection(electionName);
        if (election == null || !election.isActive()) {
            System.out.println("Election not found or closed.");
            return;
        }

        System.out.print("Enter candidate name: ");
        String candidateName = scanner.nextLine();
        VoteSys.getElectionService().addCandidateToElection(electionName, candidateName);
    }

    private static void vote(Scanner scanner) {
        System.out.print("Enter election name: ");
        String electionName = scanner.nextLine();
        Election election = VoteSys.getElectionService().getElection(electionName);

        if (election == null || !election.isActive()) {
            System.out.println("Election not found or closed.");
            return;
        }

        System.out.println("Candidates:");
        for (Candidate candidate : election.getCandidates()) {
            System.out.println(candidate.getName());
        }

        System.out.print("Enter your vote (candidate name): ");
        String votedCandidate = scanner.nextLine();
        boolean voteFound = false;
        for (Candidate candidate : election.getCandidates()) {
            if (candidate.getName().equalsIgnoreCase(votedCandidate)) {
                candidate.incrementVotes();
                voteFound = true;
                System.out.println("Vote casted successfully.");
                break;
            }
        }

        if (!voteFound) {
            System.out.println("Invalid candidate.");
        }
    }

    private static void viewResults(Scanner scanner) {
        System.out.print("Enter election name: ");
        String electionName = scanner.nextLine();
        Election election = VoteSys.getElectionService().getElection(electionName);

        if (election == null || election.isActive()) {
            System.out.println("Election results not available.");
            return;
        }

        System.out.println("Election results for " + electionName + ":");
        for (Candidate candidate : election.getCandidates()) {
            System.out.println(candidate.getName() + ": " + candidate.getVotes() + " votes");
        }
    }
}
