import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team extends BaseEntity {

    private final String name;
    private final List<User> members = new ArrayList<>();

    public Team(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название команды не может быть пустым");
        }
        this.name = name.trim();
    }

    public void addMember(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Участник не может быть null");
        }
        if (members.contains(user)) {
            throw new IllegalStateException(user.getName() + " уже в команде");
        }
        members.add(user);
    }

    public boolean hasMember(User user) {
        return members.contains(user);
    }

    public String getName() {
        return name;
    }

    // наружу отдаём неизменяемое представление, а не свой список
    public List<User> getMembers() {
        return Collections.unmodifiableList(members);
    }
}
