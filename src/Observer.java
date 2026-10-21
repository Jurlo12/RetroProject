public class Observer extends User{

    public Observer(String name, String email) {
        super(name, email);
    }

    @Override
    public boolean canManageRetro() {
        return false;
    }

    @Override
    public boolean canVote() {
        return false;
    }

    @Override
    protected String role() {
        return "Observer";
    }
}
