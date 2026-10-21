import java.time.LocalDate;

public class ActionItem extends BaseEntity {

    private final String description;
    private final User assignee;
    private final LocalDate dueDate;

    // главный конструктор: вся валидация здесь
    public ActionItem(String description, User assignee, LocalDate dueDate) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Описание действия обязательно");
        }
        if (assignee == null) {
            throw new IllegalArgumentException("Нужен исполнитель");
        }
        if (dueDate == null) {
            throw new IllegalArgumentException("Нужен срок");
        }
        this.description = description.trim();
        this.assignee = assignee;
        this.dueDate = dueDate;
    }

    // перегрузка: срок по умолчанию +14 дней (до следующего ретро), цепочка через this(...)
    public ActionItem(String description, User assignee) {
        this(description, assignee, LocalDate.now().plusDays(14));
    }

    public String getDescription() {
        return description;
    }

    public User getAssignee() {
        return assignee;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
