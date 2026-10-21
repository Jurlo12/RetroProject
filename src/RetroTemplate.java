import java.util.List;

/** Контракт: шаблон ретро задаёт название и набор колонок доски. */
public interface RetroTemplate {

    String title();

    List<String> columns();

    default int columnsCount() {
        return columns().size();
    }
}
