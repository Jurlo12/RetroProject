import java.time.Instant;

/** Общая заготовка для всех сущностей: id и время создания. */
public abstract class BaseEntity implements Identifiable {

    private static long nextId = 1;      // одно значение на весь класс (static)

    private final long id;
    private final Instant createdAt;

    protected BaseEntity() {
        this.id = nextId++;              // пока не потокобезопасно, починим в день 15
        this.createdAt = Instant.now();
    }

    @Override
    public long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
