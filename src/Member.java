public class Member extends User {

    public Member(String name, String email) {
        super(name, email);
    }

    @Override
    public boolean canManageRetro() {
        return false;
    }

    @Override
    public boolean canVote() {
        return true;
    }

    @Override
    protected String role() {
        return "MEMBER";
    }
}
