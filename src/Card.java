import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/** Наследует BaseEntity (id) и реализует Votable (голосование). */
public class Card extends BaseEntity implements Votable {

    private static final int MAX_TEXT_LENGTH = 280;

    private final User author;
    private final String column;
    private final String text;
    private final Set<User> voters = new LinkedHashSet<>();   // сохраняет порядок голосования

    public Card(User author, String column, String text) {
        if (author == null) {
            throw new IllegalArgumentException("У карточки должен быть автор");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Текст карточки не может быть пустым");
        }
        if (text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("Текст длиннее " + MAX_TEXT_LENGTH + " символов");
        }
        this.author = author;
        this.column = column;
        this.text = text.trim();
    }

    @Override
    public int votesCount() {
        return voters.size();
    }

    @Override
    public void voteBy(User voter) {
        if (voter == null) {
            throw new IllegalArgumentException("Голосующий не может быть null");
        }
        if (!voters.add(voter)) {         // add вернёт false, если уже был в множестве
            throw new IllegalStateException(
                    "Пользователь " + voter.getName() + " уже голосовал за карточку \"" + text + "\"");
        }
        if (!voter.canVote()) {
            throw new IllegalStateException(
                    "Пользователь " + voter.getName() + " не может голосовать");
        }
    }

    public User getAuthor() {
        return author;
    }

    public String getColumn() {
        return column;
    }

    public String getText() {
        return text;
    }

    public Set<User> getVoters() {
        return Collections.unmodifiableSet(voters);
    }
}
