public class ScrumMaster extends User {

    public ScrumMaster(String name, String email) {
        super(name, email);
    }

    @Override
    public boolean canManageRetro() {
        return true;
    }

    @Override
    public boolean canVote() {
        return true;
    }

    @Override
    protected String role() {
        return "SCRUM_MASTER";
    }
}