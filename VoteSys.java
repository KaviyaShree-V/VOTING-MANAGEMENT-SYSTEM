import java.util.HashMap;
import java.util.Map;

public class VoteSys {
    private static ElectionService electionService = new ElectionService();
    private static Map<String, User> users = new HashMap<>();


    public static ElectionService getElectionService() {
        return electionService;
    }

    public static void setElectionService(ElectionService electionService) {
        VoteSys.electionService = electionService;
    }

    public static Map<String,User> getUsers() {
        return users;
    }

    public static void setUsers(Map<String,User> users) {
        VoteSys.users = users;
    }
}
