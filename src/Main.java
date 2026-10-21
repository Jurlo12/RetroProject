import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. Шаблон и ретро. Переменная типа ИНТЕРФЕЙСА, объект конкретного класса
        RetroTemplate template = new FourLsTemplate();
        Retro retro = new Retro("Ретро спринта 12", template);
        System.out.println("Создано ретро #" + retro.getId()
                + " по шаблону \"" + template.title() + "\", колонки: " + template.columns());

        // 2. Участники
        ScrumMaster anna = new ScrumMaster("Аня", "anya@example.com");
        Member boris = new Member("Борис", "boris@example.com");
        Member vika = new Member("Вика", "vika@example.com");
        Observer lexa = new Observer("Леша", "drgvdf@fvfd");

        // 3. Команда и привязка к ретро
        Team team = new Team("Backend");
        team.addMember(anna);
        team.addMember(boris);
        team.addMember(vika);
        team.addMember(lexa);
        retro.assignTeam(team);
        System.out.println("В команде \"" + team.getName() + "\" участников: " + team.getMembers().size());

        // 4. Карточки «Темы для обсуждения»
        String column = "Longed for";
        Card slowReview = retro.addCard(boris, column, "Медленное code review");
        Card flakyTests = retro.addCard(vika, column, "Нестабильные тесты в CI");
        Card unclearTasks = retro.addCard(anna, column, "Нечёткие требования в задачах");

        // 5. Голосование через интерфейс Votable
        castVotes(slowReview, List.of(anna, boris, vika, lexa));
        castVotes(flakyTests, List.of(boris, vika));
        castVotes(unclearTasks, List.of(anna));

        // 5.1. Защита: повторный голос
        try {
            castVotes(slowReview, List.of(vika));
        } catch (IllegalStateException e) {
            System.out.println("Защита от двойного голоса: " + e.getMessage());
        }

        // 6. Действия: что необходимо изменить
        ActionItem reviewSla = new ActionItem(
                "Договориться о SLA на code review: ответ в течение 4 часов",
                boris, LocalDate.of(2026, 10, 30));
        ActionItem fixTests = new ActionItem(
                "Найти и починить 5 самых нестабильных тестов",
                vika);                                            // срок по умолчанию +14 дней

        retro.addActionItem(anna, reviewSla);
        retro.addActionItem(anna, fixTests);

        // 6.1. Защита: действие без прав
        try {
            retro.addActionItem(boris, new ActionItem("Что-то поменять", boris));
        } catch (IllegalStateException e) {
            System.out.println("Защита по ролям: " + e.getMessage());
        }

        // Интерфейсы в действии: один код для разных типов объектов
        List<Votable> votables = List.of(slowReview, flakyTests, unclearTasks);
        int totalVotes = 0;
        for (Votable votable : votables) {
            totalVotes += votable.votesCount();
        }
        System.out.println("Всего голосов по всем карточкам: " + totalVotes);

        List<Identifiable> entities = List.of(team, retro, slowReview, reviewSla);
        for (Identifiable entity : entities) {
            System.out.println("  id=" + entity.getId() + " -> " + entity.getClass().getSimpleName());
        }
        System.out.println();

        // 7. Итог ретро
        System.out.println(RetroReport.build(retro));
    }

    // Метод принимает ИНТЕРФЕЙС: ему всё равно, карточка это или что-то другое
    private static void castVotes(Votable item, List<User> voters) {
        for (User voter : voters) {
            item.voteBy(voter);
        }
    }
}