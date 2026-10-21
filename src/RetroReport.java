import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Формирует итоговый текстовый отчёт. Утилитный класс: объекты создавать нельзя. */
public class RetroReport {

    private RetroReport() {
    }

    public static String build(Retro retro) {
        StringBuilder sb = new StringBuilder();
        String line = "=".repeat(64);
        Team team = retro.getTeam();

        sb.append(line).append('\n');
        sb.append("РЕТРОСПЕКТИВА: ").append(retro.getTitle()).append('\n');
        sb.append("Шаблон: ").append(retro.getTemplate().title())
                .append(" (колонок: ").append(retro.getTemplate().columnsCount()).append(")\n");
        sb.append("Команда: ").append(team.getName()).append('\n');
        sb.append(line).append("\n\n");

        sb.append("УЧАСТНИКИ (").append(team.getMembers().size()).append("):\n");
        int n = 1;
        for (User user : team.getMembers()) {
            sb.append("  ").append(n++).append(". ").append(user.describe())
                    .append(" <").append(user.getEmail()).append(">\n");
        }

        sb.append("\nКАРТОЧКИ И ГОЛОСА:\n");
        Card top = null;
        for (Card card : retro.getCards()) {
            sb.append("  [").append(card.votesCount()).append(" гол.] ")
                    .append(card.getText())
                    .append(" (автор: ").append(card.getAuthor().getName()).append(")\n");
            if (card.hasVotes()) {                                   // default-метод Votable
                sb.append("            проголосовали: ").append(names(card.getVoters())).append('\n');
            }
            if (top == null || card.votesCount() > top.votesCount()) {
                top = card;
            }
        }
        if (top != null && top.hasVotes()) {
            sb.append("\nГЛАВНАЯ ТЕМА: ").append(top.getText())
                    .append(" (голосов: ").append(top.votesCount()).append(")\n");
        }

        sb.append("\nДЕЙСТВИЯ (ЧТО МЕНЯЕМ):\n");
        n = 1;
        for (ActionItem item : retro.getActionItems()) {
            sb.append("  ").append(n++).append(". ").append(item.getDescription())
                    .append(" — исполнитель: ").append(item.getAssignee().getName())
                    .append(", срок: ").append(item.getDueDate()).append('\n');
        }
        sb.append(line);
        return sb.toString();
    }

    private static String names(Collection<User> users) {
        List<String> result = new ArrayList<>();
        for (User user : users) {
            result.add(user.getName());
        }
        return String.join(", ", result);
    }
}
