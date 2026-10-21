import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Связывает всё вместе: шаблон, команду, карточки и действия. */
public class Retro extends BaseEntity {

    private final String title;
    private final RetroTemplate template;     // ссылка на ИНТЕРФЕЙС, а не на конкретный шаблон
    private Team team;                        // назначается позже, после создания участников
    private final List<Card> cards = new ArrayList<>();
    private final List<ActionItem> actionItems = new ArrayList<>();

    public Retro(String title, RetroTemplate template) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название ретро не может быть пустым");
        }
        if (template == null) {
            throw new IllegalArgumentException("Нужен шаблон ретро");
        }
        this.title = title.trim();
        this.template = template;
    }

    public void assignTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Команда не может быть null");
        }
        if (this.team != null) {
            throw new IllegalStateException("Команда уже назначена");
        }
        if (team.getMembers().isEmpty()) {
            throw new IllegalStateException("В команде нет участников");
        }
        this.team = team;
    }

    public Card addCard(User author, String column, String text) {
        requireTeam();
        if (!team.hasMember(author)) {
            throw new IllegalStateException("Автор не входит в команду");
        }
        if (!template.columns().contains(column)) {       // вызов через интерфейс
            throw new IllegalArgumentException(
                    "Колонки \"" + column + "\" нет в шаблоне " + template.title());
        }
        Card card = new Card(author, column, text);
        cards.add(card);
        return card;
    }

    public void addActionItem(User actor, ActionItem item) {
        requireTeam();
        if (!actor.canManageRetro()) {                    // полиморфный вызов: решает тип объекта
            throw new IllegalStateException(
                    actor.describe() + " не может создавать действия");
        }
        if (!team.hasMember(item.getAssignee())) {
            throw new IllegalArgumentException("Исполнитель не входит в команду");
        }
        actionItems.add(item);
    }

    private void requireTeam() {
        if (team == null) {
            throw new IllegalStateException("Сначала назначьте команду");
        }
    }

    public String getTitle() {
        return title;
    }

    public RetroTemplate getTemplate() {
        return template;
    }

    public Team getTeam() {
        return team;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<ActionItem> getActionItems() {
        return Collections.unmodifiableList(actionItems);
    }
}
